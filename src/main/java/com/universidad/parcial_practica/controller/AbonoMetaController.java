package com.universidad.parcial_practica.controller;

import com.universidad.parcial_practica.model.AbonoMeta;
import com.universidad.parcial_practica.model.Meta;
import com.universidad.parcial_practica.repository.MetaRepository;
import com.universidad.parcial_practica.service.AbonoMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gestionar los abonos a las metas de ahorro.
 * Expone endpoints bajo la ruta /api/abonos.
 */
@RestController
@RequestMapping("/api/abonos")
public class AbonoMetaController {

    @Autowired
    private AbonoMetaService abonoMetaService;

    @Autowired
    private MetaRepository metaRepository;

    /**
     * Lista todos los abonos asociados a una meta específica.
     *
     * @param metaId identificador de la meta
     * @return lista de abonos de la meta
     */
    @GetMapping("/meta/{metaId}")
    public List<AbonoMeta> listarPorMeta(@PathVariable Long metaId) {
        Meta meta = metaRepository.findById(metaId).orElse(null);
        return abonoMetaService.listarPorMeta(meta);
    }

    /**
     * Registra un nuevo abono a una meta.
     *
     * @param abono datos del abono a guardar
     * @return el abono guardado
     */
    @PostMapping
    public AbonoMeta guardar(@RequestBody AbonoMeta abono) {
        return abonoMetaService.guardar(abono);
    }

    /**
     * Elimina un abono por su identificador.
     *
     * @param id identificador del abono a eliminar
     */
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        abonoMetaService.eliminar(id);
    }
}