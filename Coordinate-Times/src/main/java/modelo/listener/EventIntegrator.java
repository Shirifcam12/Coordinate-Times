package modelo.listener;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

/**
 * Clase que ayuda integrar los envento
 * @author Luna Menguante
 */
public class EventIntegrator implements Integrator {

    public static final EventIntegrator INSTANCE = new EventIntegrator();

    @Override
    /**
     * Metodo que integra lo eventos.
     */
    public void integrate(Metadata mtdt, SessionFactoryImplementor sfi, SessionFactoryServiceRegistry sfsr) {
        final EventListenerRegistry eventListenerRegistry = sfsr.getService(EventListenerRegistry.class);
        eventListenerRegistry.appendListeners(EventType.POST_INSERT, UsuarioInsertEventListener.getInstance());
    }

    @Override
    /**
     * Metodo que desintegra los enventos.
     */
    public void disintegrate(SessionFactoryImplementor sfi, SessionFactoryServiceRegistry sfsr) {
    }

}
