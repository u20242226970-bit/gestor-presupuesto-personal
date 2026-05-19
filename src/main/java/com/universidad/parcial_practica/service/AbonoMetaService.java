package com.universidad.parcial_practica.service;

import com.universidad.parcial_practica.model.AbonoMeta;
import com.universidad.parcial_practica.model.Meta;
import com.universidad.parcial_practica.repository.AbonoMetaRepository;
import com.universidad.parcial_practica.repository.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AbonoMetaService {

    @Autowired
    private AbonoMetaRepository abonoMetaRepository;

    @Autowired
    private MetaRepository metaRepository;

    public List<AbonoMeta> listarPorMeta(Meta meta) {
        return abonoMetaRepository.findByMeta(meta);
    }

    public AbonoMeta guardar(AbonoMeta abono) {
        // IMPORTANTE: buscar la meta REAL de la BD, no usar la del request
        Meta metaReal = metaRepository.findById(abono.getMeta().getId()).orElse(null);
        if (metaReal == null) return null;

        // Actualizar solo el monto ahorrado
        double montoActual = metaReal.getMontoAhorrado() == null ? 0 : metaReal.getMontoAhorrado();
        metaReal.setMontoAhorrado(montoActual + abono.getMonto());
        metaRepository.save(metaReal);

        // Guardar el abono con la meta real
        abono.setMeta(metaReal);
        return abonoMetaRepository.save(abono);
    }

    public void eliminar(Long id) {
        abonoMetaRepository.deleteById(id);
    }
}