package com.manufacturing.manufacturingmanagementsystem.service.id;

import com.manufacturing.manufacturingmanagementsystem.model.audit.ReuseId;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.io.Serializable;
public class IdGenerator implements IdentifierGenerator{
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        try {
            ReuseId reuseId = (ReuseId) o;
            if (reuseId.getReuseId() != null) {
                return reuseId.getReuseId();
            }
        } catch (Exception e) {
            // Handle exception
        }
        return SnowFlakeIdService.getInstance().nextId();
    }

    public Long nextId() {
        return SnowFlakeIdService.getInstance().nextId();
    }
}
