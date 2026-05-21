-- ============================================================
-- TRIGGERS DE FINANCERANK - PostgreSQL
-- ============================================================

-- ============================================================
-- TRIGGER 1: AUDITORIA DE GASTOS
-- Registra cada INSERT, UPDATE y DELETE sobre la tabla gasto
-- ============================================================

CREATE TABLE IF NOT EXISTS gasto_auditoria (
    id SERIAL PRIMARY KEY,
    gasto_id BIGINT,
    operacion VARCHAR(10),
    monto_anterior DOUBLE PRECISION,
    monto_nuevo DOUBLE PRECISION,
    usuario_db VARCHAR(100),
    fecha_operacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE FUNCTION fn_auditar_gasto()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'INSERT') THEN
        INSERT INTO gasto_auditoria(gasto_id, operacion, monto_anterior, monto_nuevo, usuario_db)
        VALUES (NEW.id, 'INSERT', NULL, NEW.monto, current_user);
        RETURN NEW;
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO gasto_auditoria(gasto_id, operacion, monto_anterior, monto_nuevo, usuario_db)
        VALUES (NEW.id, 'UPDATE', OLD.monto, NEW.monto, current_user);
        RETURN NEW;
    ELSIF (TG_OP = 'DELETE') THEN
        INSERT INTO gasto_auditoria(gasto_id, operacion, monto_anterior, monto_nuevo, usuario_db)
        VALUES (OLD.id, 'DELETE', OLD.monto, NULL, current_user);
        RETURN OLD;
    END IF;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_auditar_gasto ON gasto;
CREATE TRIGGER trg_auditar_gasto
AFTER INSERT OR UPDATE OR DELETE ON gasto
FOR EACH ROW
EXECUTE FUNCTION fn_auditar_gasto();


-- ============================================================
-- TRIGGER 2: VALIDACION DE MONTO EN GASTOS
-- Impide guardar un gasto con monto nulo o menor/igual a cero
-- ============================================================

CREATE OR REPLACE FUNCTION fn_validar_monto_gasto()
RETURNS TRIGGER AS $$
BEGIN
    IF (NEW.monto IS NULL OR NEW.monto <= 0) THEN
        RAISE EXCEPTION 'El monto del gasto debe ser mayor que cero (valor recibido: %)', NEW.monto;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_validar_monto_gasto ON gasto;
CREATE TRIGGER trg_validar_monto_gasto
BEFORE INSERT OR UPDATE ON gasto
FOR EACH ROW
EXECUTE FUNCTION fn_validar_monto_gasto();


-- ============================================================
-- TRIGGER 3: VALIDACION DE MONTO EN ABONOS
-- Impide registrar un abono con monto nulo o menor/igual a cero
-- ============================================================

CREATE OR REPLACE FUNCTION fn_validar_monto_abono()
RETURNS TRIGGER AS $$
BEGIN
    IF (NEW.monto IS NULL OR NEW.monto <= 0) THEN
        RAISE EXCEPTION 'El monto del abono debe ser mayor que cero (valor recibido: %)', NEW.monto;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_validar_monto_abono ON abono_meta;
CREATE TRIGGER trg_validar_monto_abono
BEFORE INSERT OR UPDATE ON abono_meta
FOR EACH ROW
EXECUTE FUNCTION fn_validar_monto_abono();