package com.pbn.org.adsdk.library.pluginloader;

import dalvik.system.DexClassLoader;

/**
 * @author peiboning
 * @DATE 2019/03/06
 */
public class AdClassLoader extends DexClassLoader {
    public AdClassLoader(String dexPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent) {
        super(dexPath, optimizedDirectory, librarySearchPath, parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
