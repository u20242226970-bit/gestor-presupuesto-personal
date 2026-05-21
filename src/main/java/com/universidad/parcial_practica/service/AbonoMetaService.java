package com.universidad.parcial_practica.service;

import com.universidad.parcial_practica.model.AbonoMeta;
import com.universidad.parcial_practica.model.Meta;
import com.universidad.parcial_practica.repository.AbonoMetaRepository;
import com.universidad.parcial_practica.repository.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio que contiene la lógica de negocio para los abonos a metas.
 * Al guardar un abono, actualiza automáticamente el monto ahorrado de la meta.
 */
@Service
public class AbonoMetaService {

    @Autowired
    private AbonoMetaRepository abonoMetaRepository;

    @Autowired
    private MetaRepository metaRepository;

    /**
     * Lista todos los abonos asociados a una meta.
     *
     * @param meta la meta de la cual se quieren los abonos
     * @return lista de abonos de la meta
     */
    public List<AbonoMeta> listarPorMeta(Meta meta) {
        return abonoMetaRepository.findByMeta(meta);
    }

    /**
     * Guarda un nuevo abono y actualiza el monto ahorrado de la meta asociada.
     * Recupera la meta real desde la base de datos para no confiar en los datos
     * enviados en la petición, suma el abono al monto ahorrado y guarda ambos.
     *
     * @param abono datos del abono a registrar
     * @return el abono guardado, o null si la meta no existe
     */
    public AbonoMeta guardar(AbonoMeta abono) {
        // Buscar la meta real de la BD, no usar la del request
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

    /**
     * Elimina un abono por su identificador.
     *
     * @param id identificador del abono a eliminar
     */
    public void eliminar(Long id) {
        abonoMetaRepository.deleteById(id);
    }
}