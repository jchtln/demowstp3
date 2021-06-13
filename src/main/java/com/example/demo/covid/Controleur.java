package com.example.demo.covid;

import com.example.demo.covid.modele.Utilisateur;
import com.example.demo.covid.modele.Attestation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class Controleur {
    private static Map<String, Utilisateur> utilisateurs = new HashMap<>(
            Map.of("police",new Utilisateur("police","police",null,null))
    );
    private static Map<String, Attestation> attestations = new HashMap<>();

    public static Utilisateur getUtilisateur(String username) {
        return utilisateurs.get(username);
    }


    @PostMapping("/utilisateurs")
    public ResponseEntity<Utilisateur> register(@RequestBody Utilisateur utilisateur){
        if (utilisateur.getUsername()==null || utilisateur.getUsername().isBlank()){
            return ResponseEntity.badRequest().build();
        }
        if(utilisateurs.containsKey(utilisateur.getUsername())){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(utilisateur.getUsername()).toUri();


        utilisateurs.put(utilisateur.getUsername(),utilisateur);
        return ResponseEntity.created(location).body(utilisateur);
    }

    @PostMapping("/attestations")
    public ResponseEntity<Attestation> enregistre(Principal principal, @RequestBody Attestation attestation){
        if(attestation.getMotif()==null || attestation.getMotif().isBlank()){
            return ResponseEntity.badRequest().build();
        }

        Utilisateur utilisateur = utilisateurs.get(principal.getName());
        attestation.setUtilisateur(utilisateur);

        String uuid = UUID.randomUUID().toString();

        attestations.put(uuid, attestation);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(uuid).toUri();

        return ResponseEntity.created(location).body(attestation);


    }

    @GetMapping("/attestations/{cle}")
    public ResponseEntity<Attestation> verifie(Principal principal, @PathVariable String cle){
        if (!attestations.containsKey(cle)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(attestations.get(cle));
    }
}
