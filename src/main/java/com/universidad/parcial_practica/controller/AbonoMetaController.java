package com.universidad.parcial_practica.controller;

import com.universidad.parcial_practica.model.AbonoMeta;
import com.universidad.parcial_practica.model.Meta;
import com.universidad.parcial_practica.repository.MetaRepository;
import com.universidad.parcial_practica.service.AbonoMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/abonos")
public class AbonoMetaController {

    @Autowired
    private AbonoMetaService abonoMetaService;

    @Autowired
    private MetaRepository metaRepository;

    @GetMapping("/meta/{metaId}")
    public List<AbonoMeta> listarPorMeta(@PathVariable Long metaId) {
        Meta meta = metaRepository.findById(metaId).orElse(null);
        return abonoMetaService.listarPorMeta(meta);
    }

    @PostMapping
    public AbonoMeta guardar(@RequestBody AbonoMeta abono) {
        return abonoMetaService.guardar(abono);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        abonoMetaService.eliminar(id);
    }
}