package com.vp.amazonreviewsapp.util.idgenerator;

import com.vp.amazonreviewsapp.model.GenericUser;
import java.io.Serializable;
import java.util.Objects;
import java.util.Random;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;
import org.thymeleaf.util.StringUtils;

public class CustomIdentifierGenerator extends UUIDGenerator {

    private static final int NUMBER_OF_CHARS_IN_ID_PART = 5;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj)
            throws HibernateException {
        if (obj instanceof GenericUser) {
            GenericUser genericUser = (GenericUser) obj;
            Serializable id = genericUser.getId();
            if (id != null) {
                return id;
            }
        }
        final String uuid = super.generate(session, obj).toString();
        final long longTimeRandom = System.nanoTime() + System.currentTimeMillis()
                + new Random().nextLong() + Objects.hash(obj);
        final String timeHex = Long.toHexString(longTimeRandom);
        return "AWS" + (StringUtils.substring(timeHex, 0, NUMBER_OF_CHARS_IN_ID_PART)
                + StringUtils.substring(uuid, 0, NUMBER_OF_CHARS_IN_ID_PART)).toUpperCase();
    }
}
