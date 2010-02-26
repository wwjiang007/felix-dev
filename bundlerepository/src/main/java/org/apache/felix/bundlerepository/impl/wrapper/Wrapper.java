package org.apache.felix.bundlerepository.impl.wrapper;

import org.apache.felix.bundlerepository.Capability;
import org.apache.felix.bundlerepository.Repository;
import org.apache.felix.bundlerepository.RepositoryAdmin;
import org.apache.felix.bundlerepository.Requirement;
import org.apache.felix.bundlerepository.Resolver;
import org.apache.felix.bundlerepository.Resource;

public class Wrapper {

    public static org.osgi.service.obr.RepositoryAdmin wrap(RepositoryAdmin admin) {
        return new RepositoryAdminWrapper(admin);
    }

    public static org.osgi.service.obr.Resource wrap(Resource resource) {
        return new ResourceWrapper(resource);
    }

    public static org.osgi.service.obr.Repository wrap(Repository repository) {
        return new RepositoryWrapper(repository);
    }

    public static org.osgi.service.obr.Resolver wrap(Resolver resolver) {
        return new ResolverWrapper(resolver);
    }

    public static org.osgi.service.obr.Requirement wrap(Requirement resolver) {
        return new RequirementWrapper(resolver);
    }

    public static org.osgi.service.obr.Capability wrap(Capability capability) {
        return new CapabilityWrapper(capability);
    }

    public static Capability unwrap(org.osgi.service.obr.Capability capability) {
        return ((CapabilityWrapper) capability).capability;
    }

    public static Resource unwrap(org.osgi.service.obr.Resource resource) {
        return ((ResourceWrapper) resource).resource;
    }

    public static Requirement unwrap(org.osgi.service.obr.Requirement requirement) {
        return ((RequirementWrapper) requirement).requirement;
    }

    public static org.osgi.service.obr.Resource[] wrap(Resource[] resources)
    {
        org.osgi.service.obr.Resource[] res = new org.osgi.service.obr.Resource[resources.length];
        for (int i = 0; i < resources.length; i++)
        {
            res[i] = wrap(resources[i]);
        }
        return res;
    }

    public static org.osgi.service.obr.Repository[] wrap(Repository[] repositories)
    {
        org.osgi.service.obr.Repository[] rep = new org.osgi.service.obr.Repository[repositories.length];
        for (int i = 0; i < repositories.length; i++)
        {
            rep[i] = wrap(repositories[i]);
        }
        return rep;
    }

    public static org.osgi.service.obr.Requirement[] wrap(Requirement[] requirements)
    {
        org.osgi.service.obr.Requirement[] req = new org.osgi.service.obr.Requirement[requirements.length];
        for (int i = 0; i < requirements.length; i++)
        {
            req[i] = wrap(requirements[i]);
        }
        return req;
    }

    public static org.osgi.service.obr.Capability[] wrap(Capability[] capabilities)
    {
        org.osgi.service.obr.Capability[] cap = new org.osgi.service.obr.Capability[capabilities.length];
        for (int i = 0; i < capabilities.length; i++)
        {
            cap[i] = wrap(capabilities[i]);
        }
        return cap;
    }

}
