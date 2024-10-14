# Procyon Differences

## org.apache.isis:isis-parent:2.0.0-M7

Diffoscope JSON file: [org.apache.isis_isis-parent_2.0.0-M7.diffoscope.json](org.apache.isis_isis-parent_2.0.0-M7.diffoscope.json)

```diff
@@ -2,14 +2,22 @@
 package org.apache.isis.schema.common.v2;
 
 import javax.xml.bind.annotation.XmlRegistry;
 
 @XmlRegistry
 public class ObjectFactory
 {
+    public OidsDto createOidsDto() {
+        return new OidsDto();
+    }
+    
+    public PeriodDto createPeriodDto() {
+        return new PeriodDto();
+    }
+    
     public ValueDto createValueDto() {
         return new ValueDto();
     }
     
     public TypedTupleDto createTypedTupleDto() {
         return new TypedTupleDto();
     }
@@ -26,26 +34,18 @@
         return new BlobDto();
     }
     
     public ClobDto createClobDto() {
         return new ClobDto();
     }
     
-    public OidsDto createOidsDto() {
-        return new OidsDto();
-    }
-    
     public EnumDto createEnumDto() {
         return new EnumDto();
     }
     
-    public PeriodDto createPeriodDto() {
-        return new PeriodDto();
-    }
-    
     public DifferenceDto createDifferenceDto() {
         return new DifferenceDto();
     }
     
     public ValueWithTypeDto createValueWithTypeDto() {
         return new ValueWithTypeDto();
     }

```

## org.apache.synapse:Apache-Synapse:3.0.2

Diffoscope JSON file: [org.apache.synapse_Apache-Synapse_3.0.2.diffoscope.json](org.apache.synapse_Apache-Synapse_3.0.2.diffoscope.json)

```diff
@@ -10,21 +10,21 @@
     }
     
     public LogConfigurationException(final String message) {
         super(message);
         this.cause = null;
     }
     
-    public LogConfigurationException(final Throwable cause) {
-        this((cause == null) ? null : cause.toString(), cause);
-    }
-    
     public LogConfigurationException(final String message, final Throwable cause) {
-        super(message + " (Caused by " + cause + ")");
+        super(String.valueOf(message) + " (Caused by " + cause + ")");
         this.cause = null;
         this.cause = cause;
     }
     
+    public LogConfigurationException(final Throwable cause) {
+        this((cause == null) ? null : cause.toString(), cause);
+    }
+    
     public Throwable getCause() {
         return this.cause;
     }
 }

```

```diff
@@ -1,15 +1,15 @@
 
 package org.apache.commons.logging.impl;
 
 import java.util.Enumeration;
 
-class WeakHashtable$1 implements Enumeration {
+private final class WeakHashtable$1 implements Enumeration {
     public boolean hasMoreElements() {
         return this.val$enumer.hasMoreElements();
     }
     
     public Object nextElement() {
-        final WeakHashtable.Referenced nextReference = (WeakHashtable.Referenced)this.val$enumer.nextElement();
-        return WeakHashtable.Referenced.access$100(nextReference);
+        final WeakHashtable$Referenced nextReference = (WeakHashtable$Referenced)this.val$enumer.nextElement();
+        return WeakHashtable$Referenced.access$0(nextReference);
     }
 }

```

```diff
@@ -8,50 +8,38 @@
 {
     public NoOpLog() {
     }
     
     public NoOpLog(final String name) {
     }
     
-    public void trace(final Object message) {
-    }
-    
-    public void trace(final Object message, final Throwable t) {
-    }
-    
     public void debug(final Object message) {
     }
     
     public void debug(final Object message, final Throwable t) {
     }
     
-    public void info(final Object message) {
-    }
-    
-    public void info(final Object message, final Throwable t) {
-    }
-    
-    public void warn(final Object message) {
-    }
-    
-    public void warn(final Object message, final Throwable t) {
-    }
-    
     public void error(final Object message) {
     }
     
     public void error(final Object message, final Throwable t) {
     }
     
     public void fatal(final Object message) {
     }
     
     public void fatal(final Object message, final Throwable t) {
     }
     
+    public void info(final Object message) {
+    }
+    
+    public void info(final Object message, final Throwable t) {
+    }
+    
     public final boolean isDebugEnabled() {
         return false;
     }
     
     public final boolean isErrorEnabled() {
         return false;
     }
@@ -67,8 +55,20 @@
     public final boolean isTraceEnabled() {
         return false;
     }
     
     public final boolean isWarnEnabled() {
         return false;
     }
+    
+    public void trace(final Object message) {
+    }
+    
+    public void trace(final Object message, final Throwable t) {
+    }
+    
+    public void warn(final Object message) {
+    }
+    
+    public void warn(final Object message, final Throwable t) {
+    }
 }

```

```diff
@@ -1,18 +1,16 @@
 
 package org.apache.commons.logging.impl;
 
-import java.net.URL;
-import java.security.PrivilegedAction;
-import java.security.AccessController;
 import java.lang.reflect.InvocationTargetException;
-import org.apache.commons.logging.LogConfigurationException;
-import org.apache.commons.logging.Log;
 import java.util.Enumeration;
 import java.util.Vector;
+import java.net.URL;
+import org.apache.commons.logging.LogConfigurationException;
+import org.apache.commons.logging.Log;
 import java.lang.reflect.Method;
 import java.lang.reflect.Constructor;
 import java.util.Hashtable;
 import org.apache.commons.logging.LogFactory;
 
 public class LogFactoryImpl extends LogFactory
 {
@@ -36,268 +34,134 @@
     protected Constructor logConstructor;
     protected Class[] logConstructorSignature;
     protected Method logMethod;
     protected Class[] logMethodSignature;
     private boolean allowFlawedContext;
     private boolean allowFlawedDiscovery;
     private boolean allowFlawedHierarchy;
+    static /* synthetic */ Class class$java$lang$String;
+    static /* synthetic */ Class class$org$apache$commons$logging$LogFactory;
+    static /* synthetic */ Class class$org$apache$commons$logging$impl$LogFactoryImpl;
+    static /* synthetic */ Class class$org$apache$commons$logging$Log;
+    
+    static {
+        PKG_LEN = "org.apache.commons.logging.impl.".length();
+        classesToDiscover = new String[] { "org.apache.commons.logging.impl.Log4JLogger", "org.apache.commons.logging.impl.Jdk14Logger", "org.apache.commons.logging.impl.Jdk13LumberjackLogger", "org.apache.commons.logging.impl.SimpleLog" };
+    }
     
     public LogFactoryImpl() {
         this.useTCCL = true;
         this.attributes = new Hashtable();
         this.instances = new Hashtable();
         this.logConstructor = null;
-        this.logConstructorSignature = new Class[] { String.class };
+        this.logConstructorSignature = new Class[] { (LogFactoryImpl.class$java$lang$String != null) ? LogFactoryImpl.class$java$lang$String : (LogFactoryImpl.class$java$lang$String = class$("java.lang.String")) };
         this.logMethod = null;
-        this.logMethodSignature = new Class[] { LogFactory.class };
+        this.logMethodSignature = new Class[] { (LogFactoryImpl.class$org$apache$commons$logging$LogFactory != null) ? LogFactoryImpl.class$org$apache$commons$logging$LogFactory : (LogFactoryImpl.class$org$apache$commons$logging$LogFactory = class$("org.apache.commons.logging.LogFactory")) };
         this.initDiagnostics();
         if (isDiagnosticsEnabled()) {
             this.logDiagnostic("Instance created.");
         }
     }
     
-    public Object getAttribute(final String name) {
-        return this.attributes.get(name);
-    }
-    
-    public String[] getAttributeNames() {
-        final Vector names = new Vector();
-        final Enumeration keys = this.attributes.keys();
-        while (keys.hasMoreElements()) {
-            names.addElement(keys.nextElement());
-        }
-        final String[] results = new String[names.size()];
-        for (int i = 0; i < results.length; ++i) {
-            results[i] = names.elementAt(i);
-        }
-        return results;
-    }
-    
-    public Log getInstance(final Class clazz) throws LogConfigurationException {
-        return this.getInstance(clazz.getName());
-    }
-    
-    public Log getInstance(final String name) throws LogConfigurationException {
-        Log instance = (Log)this.instances.get(name);
-        if (instance == null) {
-            instance = this.newInstance(name);
-            this.instances.put(name, instance);
-        }
-        return instance;
-    }
-    
-    public void release() {
-        this.logDiagnostic("Releasing all known loggers");
-        this.instances.clear();
-    }
-    
-    public void removeAttribute(final String name) {
-        this.attributes.remove(name);
-    }
-    
-    public void setAttribute(final String name, final Object value) {
-        if (this.logConstructor != null) {
-            this.logDiagnostic("setAttribute: call too late; configuration already performed.");
-        }
-        if (value == null) {
-            this.attributes.remove(name);
-        }
-        else {
-            this.attributes.put(name, value);
-        }
-        if (name.equals("use_tccl")) {
-            this.useTCCL = Boolean.valueOf(value.toString());
-        }
-    }
-    
-    protected static ClassLoader getContextClassLoader() throws LogConfigurationException {
-        return LogFactory.getContextClassLoader();
-    }
-    
-    protected static boolean isDiagnosticsEnabled() {
-        return LogFactory.isDiagnosticsEnabled();
-    }
-    
-    protected static ClassLoader getClassLoader(final Class clazz) {
-        return LogFactory.getClassLoader(clazz);
-    }
-    
-    private void initDiagnostics() {
-        final Class clazz = this.getClass();
-        final ClassLoader classLoader = getClassLoader(clazz);
-        String classLoaderName;
-        try {
-            if (classLoader == null) {
-                classLoaderName = "BOOTLOADER";
-            }
-            else {
-                classLoaderName = LogFactory.objectId((Object)classLoader);
-            }
-        }
-        catch (final SecurityException e) {
-            classLoaderName = "UNKNOWN";
-        }
-        this.diagnosticPrefix = "[LogFactoryImpl@" + System.identityHashCode((Object)this) + " from " + classLoaderName + "] ";
-    }
-    
-    protected void logDiagnostic(final String msg) {
-        if (isDiagnosticsEnabled()) {
-            LogFactory.logRawDiagnostic(this.diagnosticPrefix + msg);
-        }
-    }
-    
-    protected String getLogClassName() {
-        if (this.logClassName == null) {
-            this.discoverLogImplementation(this.getClass().getName());
-        }
-        return this.logClassName;
-    }
-    
-    protected Constructor getLogConstructor() throws LogConfigurationException {
-        if (this.logConstructor == null) {
-            this.discoverLogImplementation(this.getClass().getName());
-        }
-        return this.logConstructor;
-    }
-    
-    protected boolean isJdk13LumberjackAvailable() {
-        return this.isLogLibraryAvailable("Jdk13Lumberjack", "org.apache.commons.logging.impl.Jdk13LumberjackLogger");
-    }
-    
-    protected boolean isJdk14Available() {
-        return this.isLogLibraryAvailable("Jdk14", "org.apache.commons.logging.impl.Jdk14Logger");
-    }
-    
-    protected boolean isLog4JAvailable() {
-        return this.isLogLibraryAvailable("Log4J", "org.apache.commons.logging.impl.Log4JLogger");
-    }
-    
-    protected Log newInstance(final String name) throws LogConfigurationException {
-        Log instance = null;
+    static /* synthetic */ Class class$(final String class$) {
         try {
-            if (this.logConstructor == null) {
-                instance = this.discoverLogImplementation(name);
-            }
-            else {
-                final Object[] params = { name };
-                instance = this.logConstructor.newInstance(params);
-            }
-            if (this.logMethod != null) {
-                final Object[] params = { this };
-                this.logMethod.invoke(instance, params);
-            }
-            return instance;
+            return Class.forName(class$);
         }
-        catch (final LogConfigurationException lce) {
-            throw lce;
-        }
-        catch (final InvocationTargetException e) {
-            final Throwable c = e.getTargetException();
-            if (c != null) {
-                throw new LogConfigurationException(c);
-            }
-            throw new LogConfigurationException((Throwable)e);
-        }
-        catch (final Throwable t) {
-            throw new LogConfigurationException(t);
+        catch (final ClassNotFoundException forName) {
+            throw new NoClassDefFoundError(forName.getMessage());
         }
     }
     
-    private static ClassLoader getContextClassLoaderInternal() throws LogConfigurationException {
-        return AccessController.doPrivileged((PrivilegedAction<ClassLoader>)new LogFactoryImpl.LogFactoryImpl$1());
-    }
-    
-    private static String getSystemProperty(final String key, final String def) throws SecurityException {
-        return AccessController.doPrivileged((PrivilegedAction<String>)new LogFactoryImpl.LogFactoryImpl$2(key, def));
-    }
-    
-    private ClassLoader getParentClassLoader(final ClassLoader cl) {
-        try {
-            return AccessController.doPrivileged((PrivilegedAction<ClassLoader>)new LogFactoryImpl.LogFactoryImpl$3(this, cl));
-        }
-        catch (final SecurityException ex) {
-            this.logDiagnostic("[SECURITY] Unable to obtain parent classloader");
-            return null;
-        }
-    }
-    
-    private boolean isLogLibraryAvailable(final String name, final String classname) {
+    private Log createLogFromClass(final String logAdapterClassName, final String logCategory, final boolean affectState) throws LogConfigurationException {
         if (isDiagnosticsEnabled()) {
-            this.logDiagnostic("Checking for '" + name + "'.");
+            this.logDiagnostic("Attempting to instantiate '" + logAdapterClassName + "'");
         }
-        try {
-            final Log log = this.createLogFromClass(classname, this.getClass().getName(), false);
-            if (log == null) {
+        final Object[] params = { logCategory };
+        Log logAdapter = null;
+        Constructor constructor = null;
+        Class logAdapterClass = null;
+        ClassLoader currentCL = this.getBaseClassLoader();
+        while (true) {
+            this.logDiagnostic("Trying to load '" + logAdapterClassName + "' from classloader " + LogFactory.objectId((Object)currentCL));
+            try {
                 if (isDiagnosticsEnabled()) {
-                    this.logDiagnostic("Did not find '" + name + "'.");
+                    final String resourceName = String.valueOf(logAdapterClassName.replace('.', '/')) + ".class";
+                    URL url;
+                    if (currentCL != null) {
+                        url = currentCL.getResource(resourceName);
+                    }
+                    else {
+                        url = ClassLoader.getSystemResource(String.valueOf(resourceName) + ".class");
+                    }
+                    if (url == null) {
+                        this.logDiagnostic("Class '" + logAdapterClassName + "' [" + resourceName + "] cannot be found.");
+                    }
+                    else {
+                        this.logDiagnostic("Class '" + logAdapterClassName + "' was found at '" + url + "'");
+                    }
                 }
-                return false;
+                Class c = null;
+                try {
+                    c = Class.forName(logAdapterClassName, true, currentCL);
+                }
+                catch (final ClassNotFoundException originalClassNotFoundException) {
+                    String msg = String.valueOf(originalClassNotFoundException.getMessage());
+                    this.logDiagnostic("The log adapter '" + logAdapterClassName + "' is not available via classloader " + LogFactory.objectId((Object)currentCL) + ": " + msg.trim());
+                    try {
+                        c = Class.forName(logAdapterClassName);
+                    }
+                    catch (final ClassNotFoundException secondaryClassNotFoundException) {
+                        msg = String.valueOf(secondaryClassNotFoundException.getMessage());
+                        this.logDiagnostic("The log adapter '" + logAdapterClassName + "' is not available via the LogFactoryImpl class classloader: " + msg.trim());
+                    }
+                }
+                constructor = c.getConstructor((Class[])this.logConstructorSignature);
+                final Object o = constructor.newInstance(params);
+                if (o instanceof Log) {
+                    logAdapterClass = c;
+                    logAdapter = (Log)o;
+                    break;
+                }
+                this.handleFlawedHierarchy(currentCL, c);
             }
-            if (isDiagnosticsEnabled()) {
-                this.logDiagnostic("Found '" + name + "'.");
+            catch (final NoClassDefFoundError e) {
+                final String msg2 = String.valueOf(e.getMessage());
+                this.logDiagnostic("The log adapter '" + logAdapterClassName + "' is missing dependencies when loaded via classloader " + LogFactory.objectId((Object)currentCL) + ": " + msg2.trim());
+                break;
             }
-            return true;
-        }
-        catch (final LogConfigurationException e) {
-            if (isDiagnosticsEnabled()) {
-                this.logDiagnostic("Logging system '" + name + "' is available but not useable.");
+            catch (final ExceptionInInitializerError e2) {
+                final String msg2 = String.valueOf(e2.getMessage());
+                this.logDiagnostic("The log adapter '" + logAdapterClassName + "' is unable to initialize itself when loaded via classloader " + LogFactory.objectId((Object)currentCL) + ": " + msg2.trim());
+                break;
             }
-            return false;
-        }
-    }
-    
-    private String getConfigurationValue(final String property) {
-        if (isDiagnosticsEnabled()) {
-            this.logDiagnostic("[ENV] Trying to get configuration for item " + property);
-        }
-        final Object valueObj = this.getAttribute(property);
-        if (valueObj != null) {
-            if (isDiagnosticsEnabled()) {
-                this.logDiagnostic("[ENV] Found LogFactory attribute [" + valueObj + "] for " + property);
+            catch (final LogConfigurationException e3) {
+                throw e3;
             }
-            return valueObj.toString();
-        }
-        if (isDiagnosticsEnabled()) {
-            this.logDiagnostic("[ENV] No LogFactory attribute found for " + property);
-        }
-        try {
-            final String value = getSystemProperty(property, null);
-            if (value != null) {
-                if (isDiagnosticsEnabled()) {
-                    this.logDiagnostic("[ENV] Found system property [" + value + "] for " + property);
-                }
-                return value;
+            catch (final Throwable t) {
+                this.handleFlawedDiscovery(logAdapterClassName, currentCL, t);
             }
-            if (isDiagnosticsEnabled()) {
-                this.logDiagnostic("[ENV] No system property found for property " + property);
+            if (currentCL == null) {
+                break;
             }
+            currentCL = currentCL.getParent();
         }
-        catch (final SecurityException e) {
-            if (isDiagnosticsEnabled()) {
-                this.logDiagnostic("[ENV] Security prevented reading system property " + property);
+        if (logAdapter != null && affectState) {
+            this.logClassName = logAdapterClassName;
+            this.logConstructor = constructor;
+            try {
+                this.logMethod = logAdapterClass.getMethod("setLogFactory", (Class[])this.logMethodSignature);
+                this.logDiagnostic("Found method setLogFactory(LogFactory) in '" + logAdapterClassName + "'");
             }
+            catch (final Throwable t2) {
+                this.logMethod = null;
+                this.logDiagnostic("[INFO] '" + logAdapterClassName + "' from classloader " + LogFactory.objectId((Object)currentCL) + " does not declare optional method " + "setLogFactory(LogFactory)");
+            }
+            this.logDiagnostic("Log adapter '" + logAdapterClassName + "' from classloader " + LogFactory.objectId((Object)logAdapterClass.getClassLoader()) + " has been selected for use.");
         }
-        if (isDiagnosticsEnabled()) {
-            this.logDiagnostic("[ENV] No configuration defined for item " + property);
-        }
-        return null;
-    }
-    
-    private boolean getBooleanConfiguration(final String key, final boolean dflt) {
-        final String val = this.getConfigurationValue(key);
-        if (val == null) {
-            return dflt;
-        }
-        return Boolean.valueOf(val);
-    }
-    
-    private void initConfiguration() {
-        this.allowFlawedContext = this.getBooleanConfiguration("org.apache.commons.logging.Log.allowFlawedContext", true);
-        this.allowFlawedDiscovery = this.getBooleanConfiguration("org.apache.commons.logging.Log.allowFlawedDiscovery", true);
-        this.allowFlawedHierarchy = this.getBooleanConfiguration("org.apache.commons.logging.Log.allowFlawedHierarchy", true);
+        return logAdapter;
     }
     
     private Log discoverLogImplementation(final String logCategory) throws LogConfigurationException {
         if (isDiagnosticsEnabled()) {
             this.logDiagnostic("Discovering a Log implementation...");
         }
         this.initConfiguration();
@@ -330,25 +194,14 @@
             if (result == null) {
                 throw new LogConfigurationException("No suitable Log implementation");
             }
             return result;
         }
     }
     
-    private void informUponSimilarName(final StringBuffer messageBuffer, final String name, final String candidate) {
-        if (name.equals(candidate)) {
-            return;
-        }
-        if (name.regionMatches(true, 0, candidate, 0, LogFactoryImpl.PKG_LEN + 5)) {
-            messageBuffer.append(" Did you mean '");
-            messageBuffer.append(candidate);
-            messageBuffer.append("'?");
-        }
-    }
-    
     private String findUserSpecifiedLogClassName() {
         if (isDiagnosticsEnabled()) {
             this.logDiagnostic("Trying to get log class from attribute 'org.apache.commons.logging.Log'");
         }
         String specifiedClass = (String)this.getAttribute("org.apache.commons.logging.Log");
         if (specifiedClass == null) {
             if (isDiagnosticsEnabled()) {
@@ -357,136 +210,64 @@
             specifiedClass = (String)this.getAttribute("org.apache.commons.logging.log");
         }
         if (specifiedClass == null) {
             if (isDiagnosticsEnabled()) {
                 this.logDiagnostic("Trying to get log class from system property 'org.apache.commons.logging.Log'");
             }
             try {
-                specifiedClass = getSystemProperty("org.apache.commons.logging.Log", null);
+                specifiedClass = System.getProperty("org.apache.commons.logging.Log");
             }
             catch (final SecurityException e) {
                 if (isDiagnosticsEnabled()) {
                     this.logDiagnostic("No access allowed to system property 'org.apache.commons.logging.Log' - " + e.getMessage());
                 }
             }
         }
         if (specifiedClass == null) {
             if (isDiagnosticsEnabled()) {
                 this.logDiagnostic("Trying to get log class from system property 'org.apache.commons.logging.log'");
             }
             try {
-                specifiedClass = getSystemProperty("org.apache.commons.logging.log", null);
+                specifiedClass = System.getProperty("org.apache.commons.logging.log");
             }
             catch (final SecurityException e) {
                 if (isDiagnosticsEnabled()) {
                     this.logDiagnostic("No access allowed to system property 'org.apache.commons.logging.log' - " + e.getMessage());
                 }
             }
         }
         if (specifiedClass != null) {
             specifiedClass = specifiedClass.trim();
         }
         return specifiedClass;
     }
     
-    private Log createLogFromClass(final String logAdapterClassName, final String logCategory, final boolean affectState) throws LogConfigurationException {
-        if (isDiagnosticsEnabled()) {
-            this.logDiagnostic("Attempting to instantiate '" + logAdapterClassName + "'");
-        }
-        final Object[] params = { logCategory };
-        Log logAdapter = null;
-        Constructor constructor = null;
-        Class logAdapterClass = null;
-        ClassLoader currentCL = this.getBaseClassLoader();
-        while (true) {
-            this.logDiagnostic("Trying to load '" + logAdapterClassName + "' from classloader " + LogFactory.objectId((Object)currentCL));
-            try {
-                if (isDiagnosticsEnabled()) {
-                    final String resourceName = logAdapterClassName.replace('.', '/') + ".class";
-                    URL url;
-                    if (currentCL != null) {
-                        url = currentCL.getResource(resourceName);
-                    }
-                    else {
-                        url = ClassLoader.getSystemResource(resourceName + ".class");
-                    }
-                    if (url == null) {
-                        this.logDiagnostic("Class '" + logAdapterClassName + "' [" + resourceName + "] cannot be found.");
-                    }
-                    else {
-                        this.logDiagnostic("Class '" + logAdapterClassName + "' was found at '" + url + "'");
-                    }
-                }
-                Class c = null;
-                try {
-                    c = Class.forName(logAdapterClassName, true, currentCL);
-                }
-                catch (final ClassNotFoundException originalClassNotFoundException) {
-                    String msg = "" + originalClassNotFoundException.getMessage();
-                    this.logDiagnostic("The log adapter '" + logAdapterClassName + "' is not available via classloader " + LogFactory.objectId((Object)currentCL) + ": " + msg.trim());
-                    try {
-                        c = Class.forName(logAdapterClassName);
-                    }
-                    catch (final ClassNotFoundException secondaryClassNotFoundException) {
-                        msg = "" + secondaryClassNotFoundException.getMessage();
-                        this.logDiagnostic("The log adapter '" + logAdapterClassName + "' is not available via the LogFactoryImpl class classloader: " + msg.trim());
-                    }
-                }
-                constructor = c.getConstructor((Class[])this.logConstructorSignature);
-                final Object o = constructor.newInstance(params);
-                if (o instanceof Log) {
-                    logAdapterClass = c;
-                    logAdapter = (Log)o;
-                    break;
-                }
-                this.handleFlawedHierarchy(currentCL, c);
-            }
-            catch (final NoClassDefFoundError e) {
-                final String msg2 = "" + e.getMessage();
-                this.logDiagnostic("The log adapter '" + logAdapterClassName + "' is missing dependencies when loaded via classloader " + LogFactory.objectId((Object)currentCL) + ": " + msg2.trim());
-                break;
-            }
-            catch (final ExceptionInInitializerError e2) {
-                final String msg2 = "" + e2.getMessage();
-                this.logDiagnostic("The log adapter '" + logAdapterClassName + "' is unable to initialize itself when loaded via classloader " + LogFactory.objectId((Object)currentCL) + ": " + msg2.trim());
-                break;
-            }
-            catch (final LogConfigurationException e3) {
-                throw e3;
-            }
-            catch (final Throwable t) {
-                this.handleFlawedDiscovery(logAdapterClassName, currentCL, t);
-            }
-            if (currentCL == null) {
-                break;
-            }
-            currentCL = this.getParentClassLoader(currentCL);
+    public Object getAttribute(final String name) {
+        return this.attributes.get(name);
+    }
+    
+    public String[] getAttributeNames() {
+        final Vector names = new Vector();
+        final Enumeration keys = this.attributes.keys();
+        while (keys.hasMoreElements()) {
+            names.addElement(keys.nextElement());
         }
-        if (logAdapter != null && affectState) {
-            this.logClassName = logAdapterClassName;
-            this.logConstructor = constructor;
-            try {
-                this.logMethod = logAdapterClass.getMethod("setLogFactory", (Class[])this.logMethodSignature);
-                this.logDiagnostic("Found method setLogFactory(LogFactory) in '" + logAdapterClassName + "'");
-            }
-            catch (final Throwable t) {
-                this.logMethod = null;
-                this.logDiagnostic("[INFO] '" + logAdapterClassName + "' from classloader " + LogFactory.objectId((Object)currentCL) + " does not declare optional method " + "setLogFactory(LogFactory)");
-            }
-            this.logDiagnostic("Log adapter '" + logAdapterClassName + "' from classloader " + LogFactory.objectId((Object)logAdapterClass.getClassLoader()) + " has been selected for use.");
+        final String[] results = new String[names.size()];
+        for (int i = 0; i < results.length; ++i) {
+            results[i] = names.elementAt(i);
         }
-        return logAdapter;
+        return results;
     }
     
     private ClassLoader getBaseClassLoader() throws LogConfigurationException {
-        final ClassLoader thisClassLoader = getClassLoader(LogFactoryImpl.class);
+        final ClassLoader thisClassLoader = getClassLoader((LogFactoryImpl.class$org$apache$commons$logging$impl$LogFactoryImpl != null) ? LogFactoryImpl.class$org$apache$commons$logging$impl$LogFactoryImpl : (LogFactoryImpl.class$org$apache$commons$logging$impl$LogFactoryImpl = class$("org.apache.commons.logging.impl.LogFactoryImpl")));
         if (!this.useTCCL) {
             return thisClassLoader;
         }
-        final ClassLoader contextClassLoader = getContextClassLoaderInternal();
+        final ClassLoader contextClassLoader = getContextClassLoader();
         final ClassLoader baseClassLoader = this.getLowestClassLoader(contextClassLoader, thisClassLoader);
         if (baseClassLoader != null) {
             if (baseClassLoader != contextClassLoader) {
                 if (!this.allowFlawedContext) {
                     throw new LogConfigurationException("Bad classloader hierarchy; LogFactoryImpl was loaded via a classloader that is not related to the current context classloader.");
                 }
                 if (isDiagnosticsEnabled()) {
@@ -500,14 +281,94 @@
                 this.logDiagnostic("[WARNING] the context classloader is not part of a parent-child relationship with the classloader that loaded LogFactoryImpl.");
             }
             return contextClassLoader;
         }
         throw new LogConfigurationException("Bad classloader hierarchy; LogFactoryImpl was loaded via a classloader that is not related to the current context classloader.");
     }
     
+    private boolean getBooleanConfiguration(final String key, final boolean dflt) {
+        final String val = this.getConfigurationValue(key);
+        if (val == null) {
+            return dflt;
+        }
+        return Boolean.valueOf(val);
+    }
+    
+    protected static ClassLoader getClassLoader(final Class clazz) {
+        return LogFactory.getClassLoader(clazz);
+    }
+    
+    private String getConfigurationValue(final String property) {
+        if (isDiagnosticsEnabled()) {
+            this.logDiagnostic("[ENV] Trying to get configuration for item " + property);
+        }
+        final Object valueObj = this.getAttribute(property);
+        if (valueObj != null) {
+            if (isDiagnosticsEnabled()) {
+                this.logDiagnostic("[ENV] Found LogFactory attribute [" + valueObj + "] for " + property);
+            }
+            return valueObj.toString();
+        }
+        if (isDiagnosticsEnabled()) {
+            this.logDiagnostic("[ENV] No LogFactory attribute found for " + property);
+        }
+        try {
+            final String value = System.getProperty(property);
+            if (value != null) {
+                if (isDiagnosticsEnabled()) {
+                    this.logDiagnostic("[ENV] Found system property [" + value + "] for " + property);
+                }
+                return value;
+            }
+            if (isDiagnosticsEnabled()) {
+                this.logDiagnostic("[ENV] No system property found for property " + property);
+            }
+        }
+        catch (final SecurityException ex) {
+            if (isDiagnosticsEnabled()) {
+                this.logDiagnostic("[ENV] Security prevented reading system property " + property);
+            }
+        }
+        if (isDiagnosticsEnabled()) {
+            this.logDiagnostic("[ENV] No configuration defined for item " + property);
+        }
+        return null;
+    }
+    
+    protected static ClassLoader getContextClassLoader() throws LogConfigurationException {
+        return LogFactory.getContextClassLoader();
+    }
+    
+    public Log getInstance(final Class clazz) throws LogConfigurationException {
+        return this.getInstance(clazz.getName());
+    }
+    
+    public Log getInstance(final String name) throws LogConfigurationException {
+        Log instance = (Log)this.instances.get(name);
+        if (instance == null) {
+            instance = this.newInstance(name);
+            this.instances.put(name, instance);
+        }
+        return instance;
+    }
+    
+    protected String getLogClassName() {
+        if (this.logClassName == null) {
+            this.discoverLogImplementation(this.getClass().getName());
+        }
+        return this.logClassName;
+    }
+    
+    protected Constructor getLogConstructor() throws LogConfigurationException {
+        if (this.logConstructor == null) {
+            this.discoverLogImplementation(this.getClass().getName());
+        }
+        return this.logConstructor;
+    }
+    
     private ClassLoader getLowestClassLoader(final ClassLoader c1, final ClassLoader c2) {
         if (c1 == null) {
             return c2;
         }
         if (c2 == null) {
             return c1;
         }
@@ -523,71 +384,57 @@
         }
         return null;
     }
     
     private void handleFlawedDiscovery(final String logAdapterClassName, final ClassLoader classLoader, final Throwable discoveryFlaw) {
         if (isDiagnosticsEnabled()) {
             this.logDiagnostic("Could not instantiate Log '" + logAdapterClassName + "' -- " + discoveryFlaw.getClass().getName() + ": " + discoveryFlaw.getLocalizedMessage());
-            if (discoveryFlaw instanceof InvocationTargetException) {
-                final InvocationTargetException ite = (InvocationTargetException)discoveryFlaw;
-                final Throwable cause = ite.getTargetException();
-                if (cause != null) {
-                    this.logDiagnostic("... InvocationTargetException: " + cause.getClass().getName() + ": " + cause.getLocalizedMessage());
-                    if (cause instanceof ExceptionInInitializerError) {
-                        final ExceptionInInitializerError eiie = (ExceptionInInitializerError)cause;
-                        final Throwable cause2 = eiie.getException();
-                        if (cause2 != null) {
-                            this.logDiagnostic("... ExceptionInInitializerError: " + cause2.getClass().getName() + ": " + cause2.getLocalizedMessage());
-                        }
-                    }
-                }
-            }
         }
         if (!this.allowFlawedDiscovery) {
             throw new LogConfigurationException(discoveryFlaw);
         }
     }
     
     private void handleFlawedHierarchy(final ClassLoader badClassLoader, final Class badClass) throws LogConfigurationException {
         boolean implementsLog = false;
-        final String logInterfaceName = Log.class.getName();
+        final String logInterfaceName = ((LogFactoryImpl.class$org$apache$commons$logging$Log != null) ? LogFactoryImpl.class$org$apache$commons$logging$Log : (LogFactoryImpl.class$org$apache$commons$logging$Log = class$("org.apache.commons.logging.Log"))).getName();
         final Class[] interfaces = badClass.getInterfaces();
         for (int i = 0; i < interfaces.length; ++i) {
             if (logInterfaceName.equals(interfaces[i].getName())) {
                 implementsLog = true;
                 break;
             }
         }
         if (implementsLog) {
             if (isDiagnosticsEnabled()) {
                 try {
-                    final ClassLoader logInterfaceClassLoader = getClassLoader(Log.class);
+                    final ClassLoader logInterfaceClassLoader = getClassLoader((LogFactoryImpl.class$org$apache$commons$logging$Log != null) ? LogFactoryImpl.class$org$apache$commons$logging$Log : (LogFactoryImpl.class$org$apache$commons$logging$Log = class$("org.apache.commons.logging.Log")));
                     this.logDiagnostic("Class '" + badClass.getName() + "' was found in classloader " + LogFactory.objectId((Object)badClassLoader) + ". It is bound to a Log interface which is not" + " the one loaded from classloader " + LogFactory.objectId((Object)logInterfaceClassLoader));
                 }
                 catch (final Throwable t) {
                     this.logDiagnostic("Error while trying to output diagnostics about bad class '" + badClass + "'");
                 }
             }
             if (!this.allowFlawedHierarchy) {
                 final StringBuffer msg = new StringBuffer();
                 msg.append("Terminating logging for this context ");
                 msg.append("due to bad log hierarchy. ");
                 msg.append("You have more than one version of '");
-                msg.append(Log.class.getName());
+                msg.append(((LogFactoryImpl.class$org$apache$commons$logging$Log != null) ? LogFactoryImpl.class$org$apache$commons$logging$Log : (LogFactoryImpl.class$org$apache$commons$logging$Log = class$("org.apache.commons.logging.Log"))).getName());
                 msg.append("' visible.");
                 if (isDiagnosticsEnabled()) {
                     this.logDiagnostic(msg.toString());
                 }
                 throw new LogConfigurationException(msg.toString());
             }
             if (isDiagnosticsEnabled()) {
                 final StringBuffer msg = new StringBuffer();
                 msg.append("Warning: bad log hierarchy. ");
                 msg.append("You have more than one version of '");
-                msg.append(Log.class.getName());
+                msg.append(((LogFactoryImpl.class$org$apache$commons$logging$Log != null) ? LogFactoryImpl.class$org$apache$commons$logging$Log : (LogFactoryImpl.class$org$apache$commons$logging$Log = class$("org.apache.commons.logging.Log"))).getName());
                 msg.append("' visible.");
                 this.logDiagnostic(msg.toString());
             }
         }
         else {
             if (!this.allowFlawedDiscovery) {
                 final StringBuffer msg = new StringBuffer();
@@ -606,12 +453,144 @@
                 msg.append(badClass.getName());
                 msg.append("' does not implement the Log interface.");
                 this.logDiagnostic(msg.toString());
             }
         }
     }
     
-    static {
-        PKG_LEN = "org.apache.commons.logging.impl.".length();
-        classesToDiscover = new String[] { "org.apache.commons.logging.impl.Log4JLogger", "org.apache.commons.logging.impl.Jdk14Logger", "org.apache.commons.logging.impl.Jdk13LumberjackLogger", "org.apache.commons.logging.impl.SimpleLog" };
+    private void informUponSimilarName(final StringBuffer messageBuffer, final String name, final String candidate) {
+        if (name.equals(candidate)) {
+            return;
+        }
+        if (name.regionMatches(true, 0, candidate, 0, LogFactoryImpl.PKG_LEN + 5)) {
+            messageBuffer.append(" Did you mean '");
+            messageBuffer.append(candidate);
+            messageBuffer.append("'?");
+        }
+    }
+    
+    private void initConfiguration() {
+        this.allowFlawedContext = this.getBooleanConfiguration("org.apache.commons.logging.Log.allowFlawedContext", true);
+        this.allowFlawedDiscovery = this.getBooleanConfiguration("org.apache.commons.logging.Log.allowFlawedDiscovery", true);
+        this.allowFlawedHierarchy = this.getBooleanConfiguration("org.apache.commons.logging.Log.allowFlawedHierarchy", true);
+    }
+    
+    private void initDiagnostics() {
+        final Class clazz = this.getClass();
+        final ClassLoader classLoader = getClassLoader(clazz);
+        String classLoaderName;
+        try {
+            if (classLoader == null) {
+                classLoaderName = "BOOTLOADER";
+            }
+            else {
+                classLoaderName = LogFactory.objectId((Object)classLoader);
+            }
+        }
+        catch (final SecurityException ex) {
+            classLoaderName = "UNKNOWN";
+        }
+        this.diagnosticPrefix = "[LogFactoryImpl@" + System.identityHashCode((Object)this) + " from " + classLoaderName + "] ";
+    }
+    
+    protected static boolean isDiagnosticsEnabled() {
+        return LogFactory.isDiagnosticsEnabled();
+    }
+    
+    protected boolean isJdk13LumberjackAvailable() {
+        return this.isLogLibraryAvailable("Jdk13Lumberjack", "org.apache.commons.logging.impl.Jdk13LumberjackLogger");
+    }
+    
+    protected boolean isJdk14Available() {
+        return this.isLogLibraryAvailable("Jdk14", "org.apache.commons.logging.impl.Jdk14Logger");
+    }
+    
+    protected boolean isLog4JAvailable() {
+        return this.isLogLibraryAvailable("Log4J", "org.apache.commons.logging.impl.Log4JLogger");
+    }
+    
+    private boolean isLogLibraryAvailable(final String name, final String classname) {
+        if (isDiagnosticsEnabled()) {
+            this.logDiagnostic("Checking for '" + name + "'.");
+        }
+        try {
+            final Log log = this.createLogFromClass(classname, this.getClass().getName(), false);
+            if (log == null) {
+                if (isDiagnosticsEnabled()) {
+                    this.logDiagnostic("Did not find '" + name + "'.");
+                }
+                return false;
+            }
+            if (isDiagnosticsEnabled()) {
+                this.logDiagnostic("Found '" + name + "'.");
+            }
+            return true;
+        }
+        catch (final LogConfigurationException ex) {
+            if (isDiagnosticsEnabled()) {
+                this.logDiagnostic("Logging system '" + name + "' is available but not useable.");
+            }
+            return false;
+        }
+    }
+    
+    protected void logDiagnostic(final String msg) {
+        if (isDiagnosticsEnabled()) {
+            LogFactory.logRawDiagnostic(String.valueOf(this.diagnosticPrefix) + msg);
+        }
+    }
+    
+    protected Log newInstance(final String name) throws LogConfigurationException {
+        Log instance = null;
+        try {
+            if (this.logConstructor == null) {
+                instance = this.discoverLogImplementation(name);
+            }
+            else {
+                final Object[] params = { name };
+                instance = this.logConstructor.newInstance(params);
+            }
+            if (this.logMethod != null) {
+                final Object[] params = { this };
+                this.logMethod.invoke(instance, params);
+            }
+            return instance;
+        }
+        catch (final LogConfigurationException lce) {
+            throw lce;
+        }
+        catch (final InvocationTargetException e) {
+            final Throwable c = e.getTargetException();
+            if (c != null) {
+                throw new LogConfigurationException(c);
+            }
+            throw new LogConfigurationException((Throwable)e);
+        }
+        catch (final Throwable t) {
+            throw new LogConfigurationException(t);
+        }
+    }
+    
+    public void release() {
+        this.logDiagnostic("Releasing all known loggers");
+        this.instances.clear();
+    }
+    
+    public void removeAttribute(final String name) {
+        this.attributes.remove(name);
+    }
+    
+    public void setAttribute(final String name, final Object value) {
+        if (this.logConstructor != null) {
+            this.logDiagnostic("setAttribute: call too late; configuration already performed.");
+        }
+        if (value == null) {
+            this.attributes.remove(name);
+        }
+        else {
+            this.attributes.put(name, value);
+        }
+        if (name.equals("use_tccl")) {
+            this.useTCCL = Boolean.valueOf(value.toString());
+        }
     }
 }

```

```diff
@@ -1,25 +1,25 @@
 
 package org.apache.commons.logging;
 
 import java.io.IOException;
 import java.security.PrivilegedAction;
 
-static class LogFactory$4 implements PrivilegedAction {
+private final class LogFactory$4 implements PrivilegedAction {
     public Object run() {
         try {
             if (this.val$loader != null) {
                 return this.val$loader.getResources(this.val$name);
             }
             return ClassLoader.getSystemResources(this.val$name);
         }
         catch (final IOException e) {
             if (LogFactory.isDiagnosticsEnabled()) {
-                LogFactory.access$000("Exception while trying to find configuration file " + this.val$name + ":" + e.getMessage());
+                LogFactory.access$0("Exception while trying to find configuration file " + this.val$name + ":" + e.getMessage());
             }
             return null;
         }
-        catch (final NoSuchMethodError e2) {
+        catch (final NoSuchMethodError noSuchMethodError) {
             return null;
         }
     }
 }

```

```diff
@@ -6,17 +6,27 @@
 import java.lang.reflect.InvocationTargetException;
 import javax.servlet.ServletContextEvent;
 import javax.servlet.ServletContextListener;
 
 public class ServletContextCleaner implements ServletContextListener
 {
     private Class[] RELEASE_SIGNATURE;
+    static /* synthetic */ Class class$java$lang$ClassLoader;
     
     public ServletContextCleaner() {
-        this.RELEASE_SIGNATURE = new Class[] { ClassLoader.class };
+        this.RELEASE_SIGNATURE = new Class[] { (ServletContextCleaner.class$java$lang$ClassLoader != null) ? ServletContextCleaner.class$java$lang$ClassLoader : (ServletContextCleaner.class$java$lang$ClassLoader = class$("java.lang.ClassLoader")) };
+    }
+    
+    static /* synthetic */ Class class$(final String class$) {
+        try {
+            return Class.forName(class$);
+        }
+        catch (final ClassNotFoundException forName) {
+            throw new NoClassDefFoundError(forName.getMessage());
+        }
     }
     
     public void contextDestroyed(final ServletContextEvent sce) {
         final ClassLoader tccl = Thread.currentThread().getContextClassLoader();
         final Object[] params = { tccl };
         ClassLoader loader = tccl;
         while (loader != null) {

```

```diff
@@ -11,26 +11,18 @@
     
     private Referenced(final Object referant) {
         this.reference = new WeakReference((T)referant);
         this.hashCode = referant.hashCode();
     }
     
     private Referenced(final Object key, final ReferenceQueue queue) {
-        this.reference = (WeakReference)new WeakHashtable.WeakKey(key, queue, this, (WeakHashtable.WeakHashtable$1)null);
+        this.reference = (WeakReference)new WeakHashtable$WeakKey((WeakHashtable$2)null, key, queue, this);
         this.hashCode = key.hashCode();
     }
     
-    public int hashCode() {
-        return this.hashCode;
-    }
-    
-    private Object getValue() {
-        return this.reference.get();
-    }
-    
     public boolean equals(final Object o) {
         boolean result = false;
         if (o instanceof Referenced) {
             final Referenced otherKey = (Referenced)o;
             final Object thisKeyValue = this.getValue();
             final Object otherKeyValue = otherKey.getValue();
             if (thisKeyValue == null) {
@@ -41,8 +33,16 @@
             }
             else {
                 result = thisKeyValue.equals(otherKeyValue);
             }
         }
         return result;
     }
+    
+    private Object getValue() {
+        return this.reference.get();
+    }
+    
+    public int hashCode() {
+        return this.hashCode;
+    }
 }

```

```diff
@@ -14,86 +14,66 @@
     public LogKitLogger(final String name) {
         this.logger = null;
         this.name = null;
         this.name = name;
         this.logger = this.getLogger();
     }
     
-    public Logger getLogger() {
-        if (this.logger == null) {
-            this.logger = Hierarchy.getDefaultHierarchy().getLoggerFor(this.name);
-        }
-        return this.logger;
-    }
-    
-    public void trace(final Object message) {
-        this.debug(message);
-    }
-    
-    public void trace(final Object message, final Throwable t) {
-        this.debug(message, t);
-    }
-    
     public void debug(final Object message) {
         if (message != null) {
             this.getLogger().debug(String.valueOf(message));
         }
     }
     
     public void debug(final Object message, final Throwable t) {
         if (message != null) {
             this.getLogger().debug(String.valueOf(message), t);
         }
     }
     
-    public void info(final Object message) {
-        if (message != null) {
-            this.getLogger().info(String.valueOf(message));
-        }
-    }
-    
-    public void info(final Object message, final Throwable t) {
+    public void error(final Object message) {
         if (message != null) {
-            this.getLogger().info(String.valueOf(message), t);
+            this.getLogger().error(String.valueOf(message));
         }
     }
     
-    public void warn(final Object message) {
+    public void error(final Object message, final Throwable t) {
         if (message != null) {
-            this.getLogger().warn(String.valueOf(message));
+            this.getLogger().error(String.valueOf(message), t);
         }
     }
     
-    public void warn(final Object message, final Throwable t) {
+    public void fatal(final Object message) {
         if (message != null) {
-            this.getLogger().warn(String.valueOf(message), t);
+            this.getLogger().fatalError(String.valueOf(message));
         }
     }
     
-    public void error(final Object message) {
+    public void fatal(final Object message, final Throwable t) {
         if (message != null) {
-            this.getLogger().error(String.valueOf(message));
+            this.getLogger().fatalError(String.valueOf(message), t);
         }
     }
     
-    public void error(final Object message, final Throwable t) {
-        if (message != null) {
-            this.getLogger().error(String.valueOf(message), t);
+    public Logger getLogger() {
+        if (this.logger == null) {
+            this.logger = Hierarchy.getDefaultHierarchy().getLoggerFor(this.name);
         }
+        return this.logger;
     }
     
-    public void fatal(final Object message) {
+    public void info(final Object message) {
         if (message != null) {
-            this.getLogger().fatalError(String.valueOf(message));
+            this.getLogger().info(String.valueOf(message));
         }
     }
     
-    public void fatal(final Object message, final Throwable t) {
+    public void info(final Object message, final Throwable t) {
         if (message != null) {
-            this.getLogger().fatalError(String.valueOf(message), t);
+            this.getLogger().info(String.valueOf(message), t);
         }
     }
     
     public boolean isDebugEnabled() {
         return this.getLogger().isDebugEnabled();
     }
     
@@ -112,8 +92,28 @@
     public boolean isTraceEnabled() {
         return this.getLogger().isDebugEnabled();
     }
     
     public boolean isWarnEnabled() {
         return this.getLogger().isWarnEnabled();
     }
+    
+    public void trace(final Object message) {
+        this.debug(message);
+    }
+    
+    public void trace(final Object message, final Throwable t) {
+        this.debug(message, t);
+    }
+    
+    public void warn(final Object message) {
+        if (message != null) {
+            this.getLogger().warn(String.valueOf(message));
+        }
+    }
+    
+    public void warn(final Object message, final Throwable t) {
+        if (message != null) {
+            this.getLogger().warn(String.valueOf(message), t);
+        }
+    }
 }

```

```diff
@@ -5,80 +5,80 @@
 import org.apache.commons.logging.Log;
 
 public class AvalonLogger implements Log
 {
     private static Logger defaultLogger;
     private transient Logger logger;
     
-    public AvalonLogger(final Logger logger) {
-        this.logger = null;
-        this.logger = logger;
+    static {
+        AvalonLogger.defaultLogger = null;
     }
     
     public AvalonLogger(final String name) {
         this.logger = null;
         if (AvalonLogger.defaultLogger == null) {
             throw new NullPointerException("default logger has to be specified if this constructor is used!");
         }
         this.logger = AvalonLogger.defaultLogger.getChildLogger(name);
     }
     
-    public Logger getLogger() {
-        return this.logger;
+    public AvalonLogger(final Logger logger) {
+        this.logger = null;
+        this.logger = logger;
     }
     
-    public static void setDefaultLogger(final Logger logger) {
-        AvalonLogger.defaultLogger = logger;
+    public void debug(final Object message) {
+        if (this.getLogger().isDebugEnabled()) {
+            this.getLogger().debug(String.valueOf(message));
+        }
     }
     
     public void debug(final Object message, final Throwable t) {
         if (this.getLogger().isDebugEnabled()) {
             this.getLogger().debug(String.valueOf(message), t);
         }
     }
     
-    public void debug(final Object message) {
-        if (this.getLogger().isDebugEnabled()) {
-            this.getLogger().debug(String.valueOf(message));
+    public void error(final Object message) {
+        if (this.getLogger().isErrorEnabled()) {
+            this.getLogger().error(String.valueOf(message));
         }
     }
     
     public void error(final Object message, final Throwable t) {
         if (this.getLogger().isErrorEnabled()) {
             this.getLogger().error(String.valueOf(message), t);
         }
     }
     
-    public void error(final Object message) {
-        if (this.getLogger().isErrorEnabled()) {
-            this.getLogger().error(String.valueOf(message));
+    public void fatal(final Object message) {
+        if (this.getLogger().isFatalErrorEnabled()) {
+            this.getLogger().fatalError(String.valueOf(message));
         }
     }
     
     public void fatal(final Object message, final Throwable t) {
         if (this.getLogger().isFatalErrorEnabled()) {
             this.getLogger().fatalError(String.valueOf(message), t);
         }
     }
     
-    public void fatal(final Object message) {
-        if (this.getLogger().isFatalErrorEnabled()) {
-            this.getLogger().fatalError(String.valueOf(message));
-        }
+    public Logger getLogger() {
+        return this.logger;
     }
     
-    public void info(final Object message, final Throwable t) {
+    public void info(final Object message) {
         if (this.getLogger().isInfoEnabled()) {
-            this.getLogger().info(String.valueOf(message), t);
+            this.getLogger().info(String.valueOf(message));
         }
     }
     
-    public void info(final Object message) {
+    public void info(final Object message, final Throwable t) {
         if (this.getLogger().isInfoEnabled()) {
-            this.getLogger().info(String.valueOf(message));
+            this.getLogger().info(String.valueOf(message), t);
         }
     }
     
     public boolean isDebugEnabled() {
         return this.getLogger().isDebugEnabled();
     }
     
@@ -98,35 +98,35 @@
         return this.getLogger().isDebugEnabled();
     }
     
     public boolean isWarnEnabled() {
         return this.getLogger().isWarnEnabled();
     }
     
-    public void trace(final Object message, final Throwable t) {
-        if (this.getLogger().isDebugEnabled()) {
-            this.getLogger().debug(String.valueOf(message), t);
-        }
+    public static void setDefaultLogger(final Logger logger) {
+        AvalonLogger.defaultLogger = logger;
     }
     
     public void trace(final Object message) {
         if (this.getLogger().isDebugEnabled()) {
             this.getLogger().debug(String.valueOf(message));
         }
     }
     
-    public void warn(final Object message, final Throwable t) {
-        if (this.getLogger().isWarnEnabled()) {
-            this.getLogger().warn(String.valueOf(message), t);
+    public void trace(final Object message, final Throwable t) {
+        if (this.getLogger().isDebugEnabled()) {
+            this.getLogger().debug(String.valueOf(message), t);
         }
     }
     
     public void warn(final Object message) {
         if (this.getLogger().isWarnEnabled()) {
             this.getLogger().warn(String.valueOf(message));
         }
     }
     
-    static {
-        AvalonLogger.defaultLogger = null;
+    public void warn(final Object message, final Throwable t) {
+        if (this.getLogger().isWarnEnabled()) {
+            this.getLogger().warn(String.valueOf(message), t);
+        }
     }
 }

```

```diff
@@ -1,10 +1,10 @@
 
 package org.apache.commons.logging;
 
 import java.security.PrivilegedAction;
 
-static class LogFactory$2 implements PrivilegedAction {
+private final class LogFactory$2 implements PrivilegedAction {
     public Object run() {
         return LogFactory.createFactory(this.val$factoryClass, this.val$classLoader);
     }
 }

```

```diff
@@ -1,13 +1,13 @@
 
 package org.apache.commons.logging;
 
 import java.security.PrivilegedAction;
 
-static class LogFactory$3 implements PrivilegedAction {
+private final class LogFactory$3 implements PrivilegedAction {
     public Object run() {
         if (this.val$loader != null) {
             return this.val$loader.getResourceAsStream(this.val$name);
         }
         return ClassLoader.getSystemResourceAsStream(this.val$name);
     }
 }

```

```diff
@@ -3,26 +3,26 @@
 
 import java.io.InputStream;
 import java.io.IOException;
 import java.util.Properties;
 import java.net.URL;
 import java.security.PrivilegedAction;
 
-static class LogFactory$5 implements PrivilegedAction {
+private final class LogFactory$5 implements PrivilegedAction {
     public Object run() {
         try {
             final InputStream stream = this.val$url.openStream();
             if (stream != null) {
                 final Properties props = new Properties();
                 props.load(stream);
                 stream.close();
                 return props;
             }
         }
-        catch (final IOException e) {
+        catch (final IOException ex) {
             if (LogFactory.isDiagnosticsEnabled()) {
-                LogFactory.access$000("Unable to read URL " + this.val$url);
+                LogFactory.access$0("Unable to read URL " + this.val$url);
             }
         }
         return null;
     }
 }

```

```diff
@@ -1,10 +1,10 @@
 
 package org.apache.commons.logging;
 
 import java.security.PrivilegedAction;
 
-static class LogFactory$1 implements PrivilegedAction {
+private final class LogFactory$1 implements PrivilegedAction {
     public Object run() {
         return LogFactory.directGetContextClassLoader();
     }
 }

```

```diff
@@ -1,22 +1,22 @@
 
 package org.apache.commons.logging.impl;
 
-import java.text.SimpleDateFormat;
-import java.io.IOException;
+import java.io.Writer;
+import java.io.PrintWriter;
+import java.io.StringWriter;
+import java.util.Date;
 import java.security.PrivilegedAction;
 import java.security.AccessController;
-import java.io.InputStream;
 import java.lang.reflect.Method;
 import java.lang.reflect.InvocationTargetException;
 import org.apache.commons.logging.LogConfigurationException;
-import java.io.Writer;
-import java.io.PrintWriter;
-import java.io.StringWriter;
-import java.util.Date;
+import java.io.InputStream;
+import java.text.SimpleDateFormat;
+import java.io.IOException;
 import java.text.DateFormat;
 import java.util.Properties;
 import java.io.Serializable;
 import org.apache.commons.logging.Log;
 
 public class SimpleLog implements Log, Serializable
 {
@@ -35,44 +35,57 @@
     public static final int LOG_LEVEL_ERROR = 5;
     public static final int LOG_LEVEL_FATAL = 6;
     public static final int LOG_LEVEL_ALL = 0;
     public static final int LOG_LEVEL_OFF = 7;
     protected String logName;
     protected int currentLogLevel;
     private String shortLogName;
+    static /* synthetic */ Class class$java$lang$Thread;
+    static /* synthetic */ Class class$org$apache$commons$logging$impl$SimpleLog;
     
-    private static String getStringProperty(final String name) {
-        String prop = null;
-        try {
-            prop = System.getProperty(name);
+    static {
+        simpleLogProps = new Properties();
+        SimpleLog.showLogName = false;
+        SimpleLog.showShortName = true;
+        SimpleLog.showDateTime = false;
+        SimpleLog.dateTimeFormat = "yyyy/MM/dd HH:mm:ss:SSS zzz";
+        SimpleLog.dateFormatter = null;
+        final InputStream in = getResourceAsStream("simplelog.properties");
+        if (in != null) {
+            try {
+                SimpleLog.simpleLogProps.load(in);
+                in.close();
+            }
+            catch (final IOException ex) {}
+        }
+        SimpleLog.showLogName = getBooleanProperty("org.apache.commons.logging.simplelog.showlogname", SimpleLog.showLogName);
+        SimpleLog.showShortName = getBooleanProperty("org.apache.commons.logging.simplelog.showShortLogname", SimpleLog.showShortName);
+        SimpleLog.showDateTime = getBooleanProperty("org.apache.commons.logging.simplelog.showdatetime", SimpleLog.showDateTime);
+        if (SimpleLog.showDateTime) {
+            SimpleLog.dateTimeFormat = getStringProperty("org.apache.commons.logging.simplelog.dateTimeFormat", SimpleLog.dateTimeFormat);
+            try {
+                SimpleLog.dateFormatter = new SimpleDateFormat(SimpleLog.dateTimeFormat);
+            }
+            catch (final IllegalArgumentException ex2) {
+                SimpleLog.dateTimeFormat = "yyyy/MM/dd HH:mm:ss:SSS zzz";
+                SimpleLog.dateFormatter = new SimpleDateFormat(SimpleLog.dateTimeFormat);
+            }
         }
-        catch (final SecurityException ex) {}
-        return (prop == null) ? SimpleLog.simpleLogProps.getProperty(name) : prop;
-    }
-    
-    private static String getStringProperty(final String name, final String dephault) {
-        final String prop = getStringProperty(name);
-        return (prop == null) ? dephault : prop;
-    }
-    
-    private static boolean getBooleanProperty(final String name, final boolean dephault) {
-        final String prop = getStringProperty(name);
-        return (prop == null) ? dephault : "true".equalsIgnoreCase(prop);
     }
     
     public SimpleLog(String name) {
         this.logName = null;
         this.shortLogName = null;
         this.logName = name;
         this.setLevel(3);
         String lvl = getStringProperty("org.apache.commons.logging.simplelog.log." + this.logName);
-        for (int i = String.valueOf(name).lastIndexOf("."); null == lvl && i > -1; lvl = getStringProperty("org.apache.commons.logging.simplelog.log." + name), i = String.valueOf(name).lastIndexOf(".")) {
+        for (int i = String.valueOf(name).lastIndexOf("."); lvl == null && i > -1; lvl = getStringProperty("org.apache.commons.logging.simplelog.log." + name), i = String.valueOf(name).lastIndexOf(".")) {
             name = name.substring(0, i);
         }
-        if (null == lvl) {
+        if (lvl == null) {
             lvl = getStringProperty("org.apache.commons.logging.simplelog.defaultlog");
         }
         if ("all".equalsIgnoreCase(lvl)) {
             this.setLevel(0);
         }
         else if ("trace".equalsIgnoreCase(lvl)) {
             this.setLevel(1);
@@ -93,31 +106,153 @@
             this.setLevel(6);
         }
         else if ("off".equalsIgnoreCase(lvl)) {
             this.setLevel(7);
         }
     }
     
-    public void setLevel(final int currentLogLevel) {
-        this.currentLogLevel = currentLogLevel;
+    static /* synthetic */ Class class$(final String class$) {
+        try {
+            return Class.forName(class$);
+        }
+        catch (final ClassNotFoundException forName) {
+            throw new NoClassDefFoundError(forName.getMessage());
+        }
+    }
+    
+    public final void debug(final Object message) {
+        if (this.isLevelEnabled(2)) {
+            this.log(2, message, null);
+        }
+    }
+    
+    public final void debug(final Object message, final Throwable t) {
+        if (this.isLevelEnabled(2)) {
+            this.log(2, message, t);
+        }
+    }
+    
+    public final void error(final Object message) {
+        if (this.isLevelEnabled(5)) {
+            this.log(5, message, null);
+        }
+    }
+    
+    public final void error(final Object message, final Throwable t) {
+        if (this.isLevelEnabled(5)) {
+            this.log(5, message, t);
+        }
+    }
+    
+    public final void fatal(final Object message) {
+        if (this.isLevelEnabled(6)) {
+            this.log(6, message, null);
+        }
+    }
+    
+    public final void fatal(final Object message, final Throwable t) {
+        if (this.isLevelEnabled(6)) {
+            this.log(6, message, t);
+        }
+    }
+    
+    private static boolean getBooleanProperty(final String name, final boolean dephault) {
+        final String prop = getStringProperty(name);
+        return (prop == null) ? dephault : "true".equalsIgnoreCase(prop);
+    }
+    
+    private static ClassLoader getContextClassLoader() {
+        ClassLoader classLoader = null;
+        if (classLoader == null) {
+            try {
+                final Method method = ((SimpleLog.class$java$lang$Thread != null) ? SimpleLog.class$java$lang$Thread : (SimpleLog.class$java$lang$Thread = class$("java.lang.Thread"))).getMethod("getContextClassLoader", (Class[])null);
+                try {
+                    classLoader = (ClassLoader)method.invoke(Thread.currentThread(), (Object[])null);
+                }
+                catch (final IllegalAccessException ex) {}
+                catch (final InvocationTargetException e) {
+                    if (!(e.getTargetException() instanceof SecurityException)) {
+                        throw new LogConfigurationException("Unexpected InvocationTargetException", e.getTargetException());
+                    }
+                }
+            }
+            catch (final NoSuchMethodException ex2) {}
+        }
+        if (classLoader == null) {
+            classLoader = ((SimpleLog.class$org$apache$commons$logging$impl$SimpleLog != null) ? SimpleLog.class$org$apache$commons$logging$impl$SimpleLog : (SimpleLog.class$org$apache$commons$logging$impl$SimpleLog = class$("org.apache.commons.logging.impl.SimpleLog"))).getClassLoader();
+        }
+        return classLoader;
     }
     
     public int getLevel() {
         return this.currentLogLevel;
     }
     
+    private static InputStream getResourceAsStream(final String name) {
+        return AccessController.doPrivileged((PrivilegedAction<InputStream>)new SimpleLog.SimpleLog$1(name));
+    }
+    
+    private static String getStringProperty(final String name) {
+        String prop = null;
+        try {
+            prop = System.getProperty(name);
+        }
+        catch (final SecurityException ex) {}
+        return (prop == null) ? SimpleLog.simpleLogProps.getProperty(name) : prop;
+    }
+    
+    private static String getStringProperty(final String name, final String dephault) {
+        final String prop = getStringProperty(name);
+        return (prop == null) ? dephault : prop;
+    }
+    
+    public final void info(final Object message) {
+        if (this.isLevelEnabled(3)) {
+            this.log(3, message, null);
+        }
+    }
+    
+    public final void info(final Object message, final Throwable t) {
+        if (this.isLevelEnabled(3)) {
+            this.log(3, message, t);
+        }
+    }
+    
+    public final boolean isDebugEnabled() {
+        return this.isLevelEnabled(2);
+    }
+    
+    public final boolean isErrorEnabled() {
+        return this.isLevelEnabled(5);
+    }
+    
+    public final boolean isFatalEnabled() {
+        return this.isLevelEnabled(6);
+    }
+    
+    public final boolean isInfoEnabled() {
+        return this.isLevelEnabled(3);
+    }
+    
+    protected boolean isLevelEnabled(final int logLevel) {
+        return logLevel >= this.currentLogLevel;
+    }
+    
+    public final boolean isTraceEnabled() {
+        return this.isLevelEnabled(1);
+    }
+    
+    public final boolean isWarnEnabled() {
+        return this.isLevelEnabled(4);
+    }
+    
     protected void log(final int type, final Object message, final Throwable t) {
         final StringBuffer buf = new StringBuffer();
         if (SimpleLog.showDateTime) {
-            final Date now = new Date();
-            final String dateText;
-            synchronized (SimpleLog.dateFormatter) {
-                dateText = SimpleLog.dateFormatter.format(now);
-            }
-            buf.append(dateText);
+            buf.append(SimpleLog.dateFormatter.format(new Date()));
             buf.append(" ");
         }
         switch (type) {
             case 1: {
                 buf.append("[TRACE] ");
                 break;
             }
@@ -162,168 +297,39 @@
             t.printStackTrace(pw);
             pw.close();
             buf.append(sw.toString());
         }
         this.write(buf);
     }
     
-    protected void write(final StringBuffer buffer) {
-        System.err.println(buffer.toString());
-    }
-    
-    protected boolean isLevelEnabled(final int logLevel) {
-        return logLevel >= this.currentLogLevel;
-    }
-    
-    public final void debug(final Object message) {
-        if (this.isLevelEnabled(2)) {
-            this.log(2, message, null);
-        }
-    }
-    
-    public final void debug(final Object message, final Throwable t) {
-        if (this.isLevelEnabled(2)) {
-            this.log(2, message, t);
-        }
+    public void setLevel(final int currentLogLevel) {
+        this.currentLogLevel = currentLogLevel;
     }
     
     public final void trace(final Object message) {
         if (this.isLevelEnabled(1)) {
             this.log(1, message, null);
         }
     }
     
     public final void trace(final Object message, final Throwable t) {
         if (this.isLevelEnabled(1)) {
             this.log(1, message, t);
         }
     }
     
-    public final void info(final Object message) {
-        if (this.isLevelEnabled(3)) {
-            this.log(3, message, null);
-        }
-    }
-    
-    public final void info(final Object message, final Throwable t) {
-        if (this.isLevelEnabled(3)) {
-            this.log(3, message, t);
-        }
-    }
-    
     public final void warn(final Object message) {
         if (this.isLevelEnabled(4)) {
             this.log(4, message, null);
         }
     }
     
     public final void warn(final Object message, final Throwable t) {
         if (this.isLevelEnabled(4)) {
             this.log(4, message, t);
         }
     }
     
-    public final void error(final Object message) {
-        if (this.isLevelEnabled(5)) {
-            this.log(5, message, null);
-        }
-    }
-    
-    public final void error(final Object message, final Throwable t) {
-        if (this.isLevelEnabled(5)) {
-            this.log(5, message, t);
-        }
-    }
-    
-    public final void fatal(final Object message) {
-        if (this.isLevelEnabled(6)) {
-            this.log(6, message, null);
-        }
-    }
-    
-    public final void fatal(final Object message, final Throwable t) {
-        if (this.isLevelEnabled(6)) {
-            this.log(6, message, t);
-        }
-    }
-    
-    public final boolean isDebugEnabled() {
-        return this.isLevelEnabled(2);
-    }
-    
-    public final boolean isErrorEnabled() {
-        return this.isLevelEnabled(5);
-    }
-    
-    public final boolean isFatalEnabled() {
-        return this.isLevelEnabled(6);
-    }
-    
-    public final boolean isInfoEnabled() {
-        return this.isLevelEnabled(3);
-    }
-    
-    public final boolean isTraceEnabled() {
-        return this.isLevelEnabled(1);
-    }
-    
-    public final boolean isWarnEnabled() {
-        return this.isLevelEnabled(4);
-    }
-    
-    private static ClassLoader getContextClassLoader() {
-        ClassLoader classLoader = null;
-        if (classLoader == null) {
-            try {
-                final Method method = Thread.class.getMethod("getContextClassLoader", (Class[])null);
-                try {
-                    classLoader = (ClassLoader)method.invoke(Thread.currentThread(), (Object[])null);
-                }
-                catch (final IllegalAccessException e) {}
-                catch (final InvocationTargetException e2) {
-                    if (!(e2.getTargetException() instanceof SecurityException)) {
-                        throw new LogConfigurationException("Unexpected InvocationTargetException", e2.getTargetException());
-                    }
-                }
-            }
-            catch (final NoSuchMethodException ex) {}
-        }
-        if (classLoader == null) {
-            classLoader = SimpleLog.class.getClassLoader();
-        }
-        return classLoader;
-    }
-    
-    private static InputStream getResourceAsStream(final String name) {
-        return AccessController.doPrivileged((PrivilegedAction<InputStream>)new SimpleLog.SimpleLog$1(name));
-    }
-    
-    static {
-        simpleLogProps = new Properties();
-        SimpleLog.showLogName = false;
-        SimpleLog.showShortName = true;
-        SimpleLog.showDateTime = false;
-        SimpleLog.dateTimeFormat = "yyyy/MM/dd HH:mm:ss:SSS zzz";
-        SimpleLog.dateFormatter = null;
-        final InputStream in = getResourceAsStream("simplelog.properties");
-        if (null != in) {
-            try {
-                SimpleLog.simpleLogProps.load(in);
-                in.close();
-            }
-            catch (final IOException ex) {}
-        }
-        SimpleLog.showLogName = getBooleanProperty("org.apache.commons.logging.simplelog.showlogname", SimpleLog.showLogName);
-        SimpleLog.showShortName = getBooleanProperty("org.apache.commons.logging.simplelog.showShortLogname", SimpleLog.showShortName);
-        SimpleLog.showDateTime = getBooleanProperty("org.apache.commons.logging.simplelog.showdatetime", SimpleLog.showDateTime);
-        if (SimpleLog.showDateTime) {
-            SimpleLog.dateTimeFormat = getStringProperty("org.apache.commons.logging.simplelog.dateTimeFormat", SimpleLog.dateTimeFormat);
-            try {
-                SimpleLog.dateFormatter = new SimpleDateFormat(SimpleLog.dateTimeFormat);
-            }
-            catch (final IllegalArgumentException e) {
-                SimpleLog.dateTimeFormat = "yyyy/MM/dd HH:mm:ss:SSS zzz";
-                SimpleLog.dateFormatter = new SimpleDateFormat(SimpleLog.dateTimeFormat);
-            }
-        }
+    protected void write(final StringBuffer buffer) {
+        System.err.println(buffer.toString());
     }
 }

```

```diff
@@ -2,18 +2,18 @@
 package org.apache.commons.logging.impl;
 
 import java.lang.ref.ReferenceQueue;
 import java.lang.ref.WeakReference;
 
 private static final class WeakKey extends WeakReference
 {
-    private final WeakHashtable.Referenced referenced;
+    private final WeakHashtable$Referenced referenced;
     
-    private WeakKey(final Object key, final ReferenceQueue queue, final WeakHashtable.Referenced referenced) {
+    private WeakKey(final Object key, final ReferenceQueue queue, final WeakHashtable$Referenced referenced) {
         super(key, queue);
         this.referenced = referenced;
     }
     
-    private WeakHashtable.Referenced getReferenced() {
+    private WeakHashtable$Referenced getReferenced() {
         return this.referenced;
     }
 }

```

```diff
@@ -1,14 +1,14 @@
 
 package org.apache.commons.logging.impl;
 
 import java.security.PrivilegedAction;
 
-static class SimpleLog$1 implements PrivilegedAction {
+private final class SimpleLog$1 implements PrivilegedAction {
     public Object run() {
-        final ClassLoader threadCL = SimpleLog.access$000();
+        final ClassLoader threadCL = SimpleLog.access$0();
         if (threadCL != null) {
             return threadCL.getResourceAsStream(this.val$name);
         }
         return ClassLoader.getSystemResourceAsStream(this.val$name);
     }
 }

```

```diff
@@ -1,12 +1,28 @@
 
 package org.apache.commons.logging;
 
 public interface Log
 {
+    void debug(final Object p0);
+    
+    void debug(final Object p0, final Throwable p1);
+    
+    void error(final Object p0);
+    
+    void error(final Object p0, final Throwable p1);
+    
+    void fatal(final Object p0);
+    
+    void fatal(final Object p0, final Throwable p1);
+    
+    void info(final Object p0);
+    
+    void info(final Object p0, final Throwable p1);
+    
     boolean isDebugEnabled();
     
     boolean isErrorEnabled();
     
     boolean isFatalEnabled();
     
     boolean isInfoEnabled();
@@ -15,27 +31,11 @@
     
     boolean isWarnEnabled();
     
     void trace(final Object p0);
     
     void trace(final Object p0, final Throwable p1);
     
-    void debug(final Object p0);
-    
-    void debug(final Object p0, final Throwable p1);
-    
-    void info(final Object p0);
-    
-    void info(final Object p0, final Throwable p1);
-    
     void warn(final Object p0);
     
     void warn(final Object p0, final Throwable p1);
-    
-    void error(final Object p0);
-    
-    void error(final Object p0, final Throwable p1);
-    
-    void fatal(final Object p0);
-    
-    void fatal(final Object p0, final Throwable p1);
 }

```

```diff
@@ -1,23 +1,38 @@
 
 package org.apache.commons.logging.impl;
 
 import org.apache.log4j.Category;
-import org.apache.log4j.Level;
 import org.apache.log4j.Priority;
 import org.apache.log4j.Logger;
 import java.io.Serializable;
 import org.apache.commons.logging.Log;
 
 public class Log4JLogger implements Log, Serializable
 {
     private static final String FQCN;
     private transient Logger logger;
     private String name;
     private static Priority traceLevel;
+    static /* synthetic */ Class class$org$apache$commons$logging$impl$Log4JLogger;
+    static /* synthetic */ Class class$org$apache$log4j$Priority;
+    static /* synthetic */ Class class$org$apache$log4j$Level;
+    
+    static {
+        FQCN = ((Log4JLogger.class$org$apache$commons$logging$impl$Log4JLogger != null) ? Log4JLogger.class$org$apache$commons$logging$impl$Log4JLogger : (Log4JLogger.class$org$apache$commons$logging$impl$Log4JLogger = class$("org.apache.commons.logging.impl.Log4JLogger"))).getName();
+        if (!((Log4JLogger.class$org$apache$log4j$Priority != null) ? Log4JLogger.class$org$apache$log4j$Priority : (Log4JLogger.class$org$apache$log4j$Priority = class$("org.apache.log4j.Priority"))).isAssignableFrom((Log4JLogger.class$org$apache$log4j$Level != null) ? Log4JLogger.class$org$apache$log4j$Level : (Log4JLogger.class$org$apache$log4j$Level = class$("org.apache.log4j.Level")))) {
+            throw new InstantiationError("Log4J 1.2 not available");
+        }
+        try {
+            Log4JLogger.traceLevel = (Priority)((Log4JLogger.class$org$apache$log4j$Level != null) ? Log4JLogger.class$org$apache$log4j$Level : (Log4JLogger.class$org$apache$log4j$Level = class$("org.apache.log4j.Level"))).getDeclaredField("TRACE").get(null);
+        }
+        catch (final Exception ex) {
+            Log4JLogger.traceLevel = Priority.DEBUG;
+        }
+    }
     
     public Log4JLogger() {
         this.logger = null;
         this.name = null;
     }
     
     public Log4JLogger(final String name) {
@@ -26,53 +41,35 @@
         this.name = name;
         this.logger = this.getLogger();
     }
     
     public Log4JLogger(final Logger logger) {
         this.logger = null;
         this.name = null;
-        if (logger == null) {
-            throw new IllegalArgumentException("Warning - null logger in constructor; possible log4j misconfiguration.");
-        }
         this.name = ((Category)logger).getName();
         this.logger = logger;
     }
     
-    public void trace(final Object message) {
-        ((Category)this.getLogger()).log(Log4JLogger.FQCN, Log4JLogger.traceLevel, message, (Throwable)null);
-    }
-    
-    public void trace(final Object message, final Throwable t) {
-        ((Category)this.getLogger()).log(Log4JLogger.FQCN, Log4JLogger.traceLevel, message, t);
+    static /* synthetic */ Class class$(final String class$) {
+        try {
+            return Class.forName(class$);
+        }
+        catch (final ClassNotFoundException forName) {
+            throw new NoClassDefFoundError(forName.getMessage());
+        }
     }
     
     public void debug(final Object message) {
         ((Category)this.getLogger()).log(Log4JLogger.FQCN, Priority.DEBUG, message, (Throwable)null);
     }
     
     public void debug(final Object message, final Throwable t) {
         ((Category)this.getLogger()).log(Log4JLogger.FQCN, Priority.DEBUG, message, t);
     }
     
-    public void info(final Object message) {
-        ((Category)this.getLogger()).log(Log4JLogger.FQCN, Priority.INFO, message, (Throwable)null);
-    }
-    
-    public void info(final Object message, final Throwable t) {
-        ((Category)this.getLogger()).log(Log4JLogger.FQCN, Priority.INFO, message, t);
-    }
-    
-    public void warn(final Object message) {
-        ((Category)this.getLogger()).log(Log4JLogger.FQCN, Priority.WARN, message, (Throwable)null);
-    }
-    
-    public void warn(final Object message, final Throwable t) {
-        ((Category)this.getLogger()).log(Log4JLogger.FQCN, Priority.WARN, message, t);
-    }
-    
     public void error(final Object message) {
         ((Category)this.getLogger()).log(Log4JLogger.FQCN, Priority.ERROR, message, (Throwable)null);
     }
     
     public void error(final Object message, final Throwable t) {
         ((Category)this.getLogger()).log(Log4JLogger.FQCN, Priority.ERROR, message, t);
     }
@@ -88,14 +85,22 @@
     public Logger getLogger() {
         if (this.logger == null) {
             this.logger = Logger.getLogger(this.name);
         }
         return this.logger;
     }
     
+    public void info(final Object message) {
+        ((Category)this.getLogger()).log(Log4JLogger.FQCN, Priority.INFO, message, (Throwable)null);
+    }
+    
+    public void info(final Object message, final Throwable t) {
+        ((Category)this.getLogger()).log(Log4JLogger.FQCN, Priority.INFO, message, t);
+    }
+    
     public boolean isDebugEnabled() {
         return ((Category)this.getLogger()).isDebugEnabled();
     }
     
     public boolean isErrorEnabled() {
         return ((Category)this.getLogger()).isEnabledFor(Priority.ERROR);
     }
@@ -112,20 +117,23 @@
         return ((Category)this.getLogger()).isEnabledFor(Log4JLogger.traceLevel);
     }
     
     public boolean isWarnEnabled() {
         return ((Category)this.getLogger()).isEnabledFor(Priority.WARN);
     }
     
-    static {
-        FQCN = Log4JLogger.class.getName();
-        if (!Priority.class.isAssignableFrom(Level.class)) {
-            throw new InstantiationError("Log4J 1.2 not available");
-        }
-        try {
-            Log4JLogger.traceLevel = (Priority)Level.class.getDeclaredField("TRACE").get(null);
-        }
-        catch (final Exception ex) {
-            Log4JLogger.traceLevel = Priority.DEBUG;
-        }
+    public void trace(final Object message) {
+        ((Category)this.getLogger()).log(Log4JLogger.FQCN, Log4JLogger.traceLevel, message, (Throwable)null);
+    }
+    
+    public void trace(final Object message, final Throwable t) {
+        ((Category)this.getLogger()).log(Log4JLogger.FQCN, Log4JLogger.traceLevel, message, t);
+    }
+    
+    public void warn(final Object message) {
+        ((Category)this.getLogger()).log(Log4JLogger.FQCN, Priority.WARN, message, (Throwable)null);
+    }
+    
+    public void warn(final Object message, final Throwable t) {
+        ((Category)this.getLogger()).log(Log4JLogger.FQCN, Priority.WARN, message, t);
     }
 }

```

```diff
@@ -8,125 +8,124 @@
 public class LogSource
 {
     protected static Hashtable logs;
     protected static boolean log4jIsAvailable;
     protected static boolean jdk14IsAvailable;
     protected static Constructor logImplctor;
     
-    private LogSource() {
-    }
-    
-    public static void setLogImplementation(final String classname) throws LinkageError, ExceptionInInitializerError, NoSuchMethodException, SecurityException, ClassNotFoundException {
-        try {
-            final Class logclass = Class.forName(classname);
-            final Class[] argtypes = { "".getClass() };
-            LogSource.logImplctor = logclass.getConstructor((Class[])argtypes);
-        }
-        catch (final Throwable t) {
-            LogSource.logImplctor = null;
-        }
-    }
-    
-    public static void setLogImplementation(final Class logclass) throws LinkageError, ExceptionInInitializerError, NoSuchMethodException, SecurityException {
-        final Class[] argtypes = { "".getClass() };
-        LogSource.logImplctor = logclass.getConstructor((Class[])argtypes);
-    }
-    
-    public static Log getInstance(final String name) {
-        Log log = (Log)LogSource.logs.get(name);
-        if (null == log) {
-            log = makeNewLogInstance(name);
-            LogSource.logs.put(name, log);
-        }
-        return log;
-    }
-    
-    public static Log getInstance(final Class clazz) {
-        return getInstance(clazz.getName());
-    }
-    
-    public static Log makeNewLogInstance(final String name) {
-        Log log = null;
-        try {
-            final Object[] args = { name };
-            log = LogSource.logImplctor.newInstance(args);
-        }
-        catch (final Throwable t) {
-            log = null;
-        }
-        if (null == log) {
-            log = (Log)new NoOpLog(name);
-        }
-        return log;
-    }
-    
-    public static String[] getLogNames() {
-        return (String[])LogSource.logs.keySet().toArray(new String[LogSource.logs.size()]);
-    }
-    
     static {
         LogSource.logs = new Hashtable();
         LogSource.log4jIsAvailable = false;
         LogSource.jdk14IsAvailable = false;
         LogSource.logImplctor = null;
         try {
-            if (null != Class.forName("org.apache.log4j.Logger")) {
+            if (Class.forName("org.apache.log4j.Logger") != null) {
                 LogSource.log4jIsAvailable = true;
             }
             else {
                 LogSource.log4jIsAvailable = false;
             }
         }
         catch (final Throwable t) {
             LogSource.log4jIsAvailable = false;
         }
         try {
-            if (null != Class.forName("java.util.logging.Logger") && null != Class.forName("org.apache.commons.logging.impl.Jdk14Logger")) {
+            if (Class.forName("java.util.logging.Logger") != null && Class.forName("org.apache.commons.logging.impl.Jdk14Logger") != null) {
                 LogSource.jdk14IsAvailable = true;
             }
             else {
                 LogSource.jdk14IsAvailable = false;
             }
         }
-        catch (final Throwable t) {
+        catch (final Throwable t2) {
             LogSource.jdk14IsAvailable = false;
         }
         String name = null;
         try {
             name = System.getProperty("org.apache.commons.logging.log");
             if (name == null) {
                 name = System.getProperty("org.apache.commons.logging.Log");
             }
         }
         catch (final Throwable t3) {}
         if (name != null) {
             try {
                 setLogImplementation(name);
+                return;
             }
-            catch (final Throwable t2) {
+            catch (final Throwable t4) {
                 try {
                     setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
                 }
-                catch (final Throwable t4) {}
+                catch (final Throwable t5) {}
             }
         }
-        else {
-            try {
-                if (LogSource.log4jIsAvailable) {
-                    setLogImplementation("org.apache.commons.logging.impl.Log4JLogger");
-                }
-                else if (LogSource.jdk14IsAvailable) {
-                    setLogImplementation("org.apache.commons.logging.impl.Jdk14Logger");
-                }
-                else {
-                    setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
-                }
+        try {
+            if (LogSource.log4jIsAvailable) {
+                setLogImplementation("org.apache.commons.logging.impl.Log4JLogger");
             }
-            catch (final Throwable t2) {
-                try {
-                    setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
-                }
-                catch (final Throwable t5) {}
+            else if (LogSource.jdk14IsAvailable) {
+                setLogImplementation("org.apache.commons.logging.impl.Jdk14Logger");
+            }
+            else {
+                setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
+            }
+        }
+        catch (final Throwable t6) {
+            try {
+                setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
             }
+            catch (final Throwable t7) {}
+        }
+    }
+    
+    private LogSource() {
+    }
+    
+    public static Log getInstance(final Class clazz) {
+        return getInstance(clazz.getName());
+    }
+    
+    public static Log getInstance(final String name) {
+        Log log = (Log)LogSource.logs.get(name);
+        if (log == null) {
+            log = makeNewLogInstance(name);
+            LogSource.logs.put(name, log);
+        }
+        return log;
+    }
+    
+    public static String[] getLogNames() {
+        return (String[])LogSource.logs.keySet().toArray(new String[LogSource.logs.size()]);
+    }
+    
+    public static Log makeNewLogInstance(final String name) {
+        Log log = null;
+        try {
+            final Object[] args = { name };
+            log = LogSource.logImplctor.newInstance(args);
+        }
+        catch (final Throwable t) {
+            log = null;
+        }
+        if (log == null) {
+            log = (Log)new NoOpLog(name);
+        }
+        return log;
+    }
+    
+    public static void setLogImplementation(final Class logclass) throws LinkageError, ExceptionInInitializerError, NoSuchMethodException, SecurityException {
+        final Class[] argtypes = { "".getClass() };
+        LogSource.logImplctor = logclass.getConstructor((Class[])argtypes);
+    }
+    
+    public static void setLogImplementation(final String classname) throws LinkageError, ExceptionInInitializerError, NoSuchMethodException, SecurityException, ClassNotFoundException {
+        try {
+            final Class logclass = Class.forName(classname);
+            final Class[] argtypes = { "".getClass() };
+            LogSource.logImplctor = logclass.getConstructor((Class[])argtypes);
+        }
+        catch (final Throwable t) {
+            LogSource.logImplctor = null;
         }
     }
 }

```

```diff
@@ -14,45 +14,65 @@
     }
     
     public boolean equals(final Object o) {
         boolean result = false;
         if (o != null && o instanceof Map.Entry) {
             final Map.Entry entry = (Map.Entry)o;
             boolean b = false;
-            Label_0093: {
-                Label_0092: {
-                    if (this.getKey() == null) {
-                        if (entry.getKey() != null) {
-                            break Label_0092;
+            Label_0095: {
+                Label_0090: {
+                    Label_0054: {
+                        boolean equals;
+                        if (this.getKey() == null) {
+                            if (entry.getKey() == null) {
+                                break Label_0054;
+                            }
+                            equals = false;
+                        }
+                        else {
+                            equals = this.getKey().equals(entry.getKey());
+                        }
+                        if (!equals) {
+                            break Label_0090;
                         }
                     }
-                    else if (!this.getKey().equals(entry.getKey())) {
-                        break Label_0092;
-                    }
-                    if ((this.getValue() != null) ? this.getValue().equals(entry.getValue()) : (entry.getValue() == null)) {
-                        b = true;
-                        break Label_0093;
+                    Label_0094: {
+                        boolean equals2;
+                        if (this.getValue() == null) {
+                            if (entry.getValue() == null) {
+                                break Label_0094;
+                            }
+                            equals2 = false;
+                        }
+                        else {
+                            equals2 = this.getValue().equals(entry.getValue());
+                        }
+                        if (!equals2) {
+                            break Label_0090;
+                        }
                     }
+                    b = true;
+                    break Label_0095;
                 }
                 b = false;
             }
             result = b;
         }
         return result;
     }
     
-    public int hashCode() {
-        return ((this.getKey() == null) ? 0 : this.getKey().hashCode()) ^ ((this.getValue() == null) ? 0 : this.getValue().hashCode());
-    }
-    
-    public Object setValue(final Object value) {
-        throw new UnsupportedOperationException("Entry.setValue is not supported.");
+    public Object getKey() {
+        return this.key;
     }
     
     public Object getValue() {
         return this.value;
     }
     
-    public Object getKey() {
-        return this.key;
+    public int hashCode() {
+        return ((this.getKey() == null) ? 0 : this.getKey().hashCode()) ^ ((this.getValue() == null) ? 0 : this.getValue().hashCode());
+    }
+    
+    public Object setValue(final Object value) {
+        throw new UnsupportedOperationException("Entry.setValue is not supported.");
     }
 }

```

```diff
@@ -1,25 +1,25 @@
 
 package org.apache.commons.logging;
 
 import java.io.IOException;
 import java.io.OutputStream;
 import java.io.FileOutputStream;
-import java.net.URL;
-import java.lang.reflect.Method;
-import java.lang.reflect.InvocationTargetException;
-import java.security.PrivilegedAction;
-import java.security.AccessController;
-import java.util.Enumeration;
 import java.io.InputStream;
-import java.util.Properties;
 import java.io.UnsupportedEncodingException;
 import java.io.Reader;
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
+import java.security.PrivilegedAction;
+import java.security.AccessController;
+import java.util.Enumeration;
+import java.net.URL;
+import java.util.Properties;
+import java.lang.reflect.Method;
+import java.lang.reflect.InvocationTargetException;
 import java.util.Hashtable;
 import java.io.PrintStream;
 
 public abstract class LogFactory
 {
     public static final String PRIORITY_KEY = "priority";
     public static final String TCCL_KEY = "use_tccl";
@@ -31,41 +31,125 @@
     private static PrintStream diagnosticsStream;
     private static String diagnosticPrefix;
     public static final String HASHTABLE_IMPLEMENTATION_PROPERTY = "org.apache.commons.logging.LogFactory.HashtableImpl";
     private static final String WEAK_HASHTABLE_CLASSNAME = "org.apache.commons.logging.impl.WeakHashtable";
     private static ClassLoader thisClassLoader;
     protected static Hashtable factories;
     protected static LogFactory nullClassLoaderFactory;
+    static /* synthetic */ Class class$org$apache$commons$logging$LogFactory;
+    static /* synthetic */ Class class$java$lang$Thread;
     
-    protected LogFactory() {
+    static {
+        LogFactory.diagnosticsStream = null;
+        LogFactory.factories = null;
+        LogFactory.nullClassLoaderFactory = null;
+        LogFactory.thisClassLoader = getClassLoader((LogFactory.class$org$apache$commons$logging$LogFactory != null) ? LogFactory.class$org$apache$commons$logging$LogFactory : (LogFactory.class$org$apache$commons$logging$LogFactory = class$("org.apache.commons.logging.LogFactory")));
+        initDiagnostics();
+        logClassLoaderEnvironment((LogFactory.class$org$apache$commons$logging$LogFactory != null) ? LogFactory.class$org$apache$commons$logging$LogFactory : (LogFactory.class$org$apache$commons$logging$LogFactory = class$("org.apache.commons.logging.LogFactory")));
+        LogFactory.factories = createFactoryStore();
+        if (isDiagnosticsEnabled()) {
+            logDiagnostic("BOOTSTRAP COMPLETED");
+        }
     }
     
-    public abstract Object getAttribute(final String p0);
-    
-    public abstract String[] getAttributeNames();
-    
-    public abstract Log getInstance(final Class p0) throws LogConfigurationException;
-    
-    public abstract Log getInstance(final String p0) throws LogConfigurationException;
-    
-    public abstract void release();
+    protected LogFactory() {
+    }
     
-    public abstract void removeAttribute(final String p0);
+    private static void cacheFactory(final ClassLoader classLoader, final LogFactory factory) {
+        if (factory != null) {
+            if (classLoader == null) {
+                LogFactory.nullClassLoaderFactory = factory;
+            }
+            else {
+                LogFactory.factories.put(classLoader, factory);
+            }
+        }
+    }
     
-    public abstract void setAttribute(final String p0, final Object p1);
+    static /* synthetic */ Class class$(final String class$) {
+        try {
+            return Class.forName(class$);
+        }
+        catch (final ClassNotFoundException forName) {
+            throw new NoClassDefFoundError(forName.getMessage());
+        }
+    }
     
-    private static final Hashtable createFactoryStore() {
-        Hashtable result = null;
-        String storeImplementationClass;
+    protected static Object createFactory(final String factoryClass, final ClassLoader classLoader) {
+        Class logFactoryClass = null;
         try {
-            storeImplementationClass = getSystemProperty("org.apache.commons.logging.LogFactory.HashtableImpl", null);
+            if (classLoader != null) {
+                try {
+                    logFactoryClass = classLoader.loadClass(factoryClass);
+                    if (((LogFactory.class$org$apache$commons$logging$LogFactory != null) ? LogFactory.class$org$apache$commons$logging$LogFactory : (LogFactory.class$org$apache$commons$logging$LogFactory = class$("org.apache.commons.logging.LogFactory"))).isAssignableFrom(logFactoryClass)) {
+                        if (isDiagnosticsEnabled()) {
+                            logDiagnostic("Loaded class " + logFactoryClass.getName() + " from classloader " + objectId((Object)classLoader));
+                        }
+                    }
+                    else if (isDiagnosticsEnabled()) {
+                        logDiagnostic("Factory class " + logFactoryClass.getName() + " loaded from classloader " + objectId((Object)logFactoryClass.getClassLoader()) + " does not extend '" + ((LogFactory.class$org$apache$commons$logging$LogFactory != null) ? LogFactory.class$org$apache$commons$logging$LogFactory : (LogFactory.class$org$apache$commons$logging$LogFactory = class$("org.apache.commons.logging.LogFactory"))).getName() + "' as loaded by this classloader.");
+                        logHierarchy("[BAD CL TREE] ", classLoader);
+                    }
+                    return logFactoryClass.newInstance();
+                }
+                catch (final ClassNotFoundException ex) {
+                    if (classLoader == LogFactory.thisClassLoader) {
+                        if (isDiagnosticsEnabled()) {
+                            logDiagnostic("Unable to locate any class called '" + factoryClass + "' via classloader " + objectId((Object)classLoader));
+                        }
+                        throw ex;
+                    }
+                }
+                catch (final NoClassDefFoundError e) {
+                    if (classLoader == LogFactory.thisClassLoader) {
+                        if (isDiagnosticsEnabled()) {
+                            logDiagnostic("Class '" + factoryClass + "' cannot be loaded" + " via classloader " + objectId((Object)classLoader) + " - it depends on some other class that cannot" + " be found.");
+                        }
+                        throw e;
+                    }
+                }
+                catch (final ClassCastException ex3) {
+                    if (classLoader == LogFactory.thisClassLoader) {
+                        final boolean implementsLogFactory = implementsLogFactory(logFactoryClass);
+                        String msg = "The application has specified that a custom LogFactory implementation should be used but Class '" + factoryClass + "' cannot be converted to '" + ((LogFactory.class$org$apache$commons$logging$LogFactory != null) ? LogFactory.class$org$apache$commons$logging$LogFactory : (LogFactory.class$org$apache$commons$logging$LogFactory = class$("org.apache.commons.logging.LogFactory"))).getName() + "'. ";
+                        if (implementsLogFactory) {
+                            msg = String.valueOf(msg) + "The conflict is caused by the presence of multiple LogFactory classes in incompatible classloaders. " + "Background can be found in http://jakarta.apache.org/commons/logging/tech.html. " + "If you have not explicitly specified a custom LogFactory then it is likely that " + "the container has set one without your knowledge. " + "In this case, consider using the commons-logging-adapters.jar file or " + "specifying the standard LogFactory from the command line. ";
+                        }
+                        else {
+                            msg = String.valueOf(msg) + "Please check the custom implementation. ";
+                        }
+                        msg = String.valueOf(msg) + "Help can be found @http://jakarta.apache.org/commons/logging/troubleshooting.html.";
+                        if (isDiagnosticsEnabled()) {
+                            logDiagnostic(msg);
+                        }
+                        final ClassCastException ex2 = new ClassCastException(msg);
+                        throw ex2;
+                    }
+                }
+            }
+            if (isDiagnosticsEnabled()) {
+                logDiagnostic("Unable to load factory class via classloader " + objectId((Object)classLoader) + " - trying the classloader associated with this LogFactory.");
+            }
+            logFactoryClass = Class.forName(factoryClass);
+            return logFactoryClass.newInstance();
         }
-        catch (final SecurityException ex) {
-            storeImplementationClass = null;
+        catch (final Exception e2) {
+            if (isDiagnosticsEnabled()) {
+                logDiagnostic("Unable to create LogFactory instance.");
+            }
+            if (logFactoryClass != null && !((LogFactory.class$org$apache$commons$logging$LogFactory != null) ? LogFactory.class$org$apache$commons$logging$LogFactory : (LogFactory.class$org$apache$commons$logging$LogFactory = class$("org.apache.commons.logging.LogFactory"))).isAssignableFrom(logFactoryClass)) {
+                return new LogConfigurationException("The chosen LogFactory implementation does not extend LogFactory. Please check your configuration.", (Throwable)e2);
+            }
+            return new LogConfigurationException((Throwable)e2);
         }
+    }
+    
+    private static final Hashtable createFactoryStore() {
+        Hashtable result = null;
+        String storeImplementationClass = System.getProperty("org.apache.commons.logging.LogFactory.HashtableImpl");
         if (storeImplementationClass == null) {
             storeImplementationClass = "org.apache.commons.logging.impl.WeakHashtable";
         }
         try {
             final Class implementationClass = Class.forName(storeImplementationClass);
             result = implementationClass.newInstance();
         }
@@ -81,23 +165,136 @@
         }
         if (result == null) {
             result = new Hashtable();
         }
         return result;
     }
     
-    private static String trim(final String src) {
-        if (src == null) {
-            return null;
+    protected static ClassLoader directGetContextClassLoader() throws LogConfigurationException {
+        ClassLoader classLoader = null;
+        try {
+            final Method method = ((LogFactory.class$java$lang$Thread != null) ? LogFactory.class$java$lang$Thread : (LogFactory.class$java$lang$Thread = class$("java.lang.Thread"))).getMethod("getContextClassLoader", (Class[])null);
+            try {
+                classLoader = (ClassLoader)method.invoke(Thread.currentThread(), (Object[])null);
+            }
+            catch (final IllegalAccessException e) {
+                throw new LogConfigurationException("Unexpected IllegalAccessException", (Throwable)e);
+            }
+            catch (final InvocationTargetException e2) {
+                if (!(e2.getTargetException() instanceof SecurityException)) {
+                    throw new LogConfigurationException("Unexpected InvocationTargetException", e2.getTargetException());
+                }
+                return classLoader;
+            }
+        }
+        catch (final NoSuchMethodException ex) {
+            classLoader = getClassLoader((LogFactory.class$org$apache$commons$logging$LogFactory != null) ? LogFactory.class$org$apache$commons$logging$LogFactory : (LogFactory.class$org$apache$commons$logging$LogFactory = class$("org.apache.commons.logging.LogFactory")));
+        }
+        return classLoader;
+    }
+    
+    public abstract Object getAttribute(final String p0);
+    
+    public abstract String[] getAttributeNames();
+    
+    private static LogFactory getCachedFactory(final ClassLoader contextClassLoader) {
+        LogFactory factory = null;
+        if (contextClassLoader == null) {
+            factory = LogFactory.nullClassLoaderFactory;
+        }
+        else {
+            factory = LogFactory.factories.get(contextClassLoader);
+        }
+        return factory;
+    }
+    
+    protected static ClassLoader getClassLoader(final Class clazz) {
+        try {
+            return clazz.getClassLoader();
+        }
+        catch (final SecurityException ex) {
+            if (isDiagnosticsEnabled()) {
+                logDiagnostic("Unable to get classloader for class '" + clazz + "' due to security restrictions - " + ex.getMessage());
+            }
+            throw ex;
+        }
+    }
+    
+    private static final Properties getConfigurationFile(final ClassLoader classLoader, final String fileName) {
+        Properties props = null;
+        double priority = 0.0;
+        URL propsUrl = null;
+        try {
+            final Enumeration urls = getResources(classLoader, fileName);
+            if (urls == null) {
+                return null;
+            }
+            while (urls.hasMoreElements()) {
+                final URL url = (URL)urls.nextElement();
+                final Properties newProps = getProperties(url);
+                if (newProps != null) {
+                    if (props == null) {
+                        propsUrl = url;
+                        props = newProps;
+                        final String priorityStr = props.getProperty("priority");
+                        priority = 0.0;
+                        if (priorityStr != null) {
+                            priority = Double.parseDouble(priorityStr);
+                        }
+                        if (!isDiagnosticsEnabled()) {
+                            continue;
+                        }
+                        logDiagnostic("[LOOKUP] Properties file found at '" + url + "'" + " with priority " + priority);
+                    }
+                    else {
+                        final String newPriorityStr = newProps.getProperty("priority");
+                        double newPriority = 0.0;
+                        if (newPriorityStr != null) {
+                            newPriority = Double.parseDouble(newPriorityStr);
+                        }
+                        if (newPriority > priority) {
+                            if (isDiagnosticsEnabled()) {
+                                logDiagnostic("[LOOKUP] Properties file at '" + url + "'" + " with priority " + newPriority + " overrides file at '" + propsUrl + "'" + " with priority " + priority);
+                            }
+                            propsUrl = url;
+                            props = newProps;
+                            priority = newPriority;
+                        }
+                        else {
+                            if (!isDiagnosticsEnabled()) {
+                                continue;
+                            }
+                            logDiagnostic("[LOOKUP] Properties file at '" + url + "'" + " with priority " + newPriority + " does not override file at '" + propsUrl + "'" + " with priority " + priority);
+                        }
+                    }
+                }
+            }
+        }
+        catch (final SecurityException ex) {
+            if (isDiagnosticsEnabled()) {
+                logDiagnostic("SecurityException thrown while trying to find/read config files.");
+            }
+        }
+        if (isDiagnosticsEnabled()) {
+            if (props == null) {
+                logDiagnostic("[LOOKUP] No properties file of name '" + fileName + "' found.");
+            }
+            else {
+                logDiagnostic("[LOOKUP] Properties file of name '" + fileName + "' found at '" + propsUrl + '\"');
+            }
         }
-        return src.trim();
+        return props;
+    }
+    
+    protected static ClassLoader getContextClassLoader() throws LogConfigurationException {
+        return AccessController.doPrivileged((PrivilegedAction<ClassLoader>)new LogFactory.LogFactory$1());
     }
     
     public static LogFactory getFactory() throws LogConfigurationException {
-        final ClassLoader contextClassLoader = getContextClassLoaderInternal();
+        final ClassLoader contextClassLoader = getContextClassLoader();
         if (contextClassLoader == null && isDiagnosticsEnabled()) {
             logDiagnostic("Context classloader is null.");
         }
         LogFactory factory = getCachedFactory(contextClassLoader);
         if (factory != null) {
             return factory;
         }
@@ -113,49 +310,49 @@
                 baseClassLoader = LogFactory.thisClassLoader;
             }
         }
         if (isDiagnosticsEnabled()) {
             logDiagnostic("[LOOKUP] Looking for system property [org.apache.commons.logging.LogFactory] to define the LogFactory subclass to use...");
         }
         try {
-            final String factoryClass = getSystemProperty("org.apache.commons.logging.LogFactory", null);
+            final String factoryClass = System.getProperty("org.apache.commons.logging.LogFactory");
             if (factoryClass != null) {
                 if (isDiagnosticsEnabled()) {
                     logDiagnostic("[LOOKUP] Creating an instance of LogFactory class '" + factoryClass + "' as specified by system property " + "org.apache.commons.logging.LogFactory");
                 }
                 factory = newFactory(factoryClass, baseClassLoader, contextClassLoader);
             }
             else if (isDiagnosticsEnabled()) {
                 logDiagnostic("[LOOKUP] No system property [org.apache.commons.logging.LogFactory] defined.");
             }
         }
         catch (final SecurityException e) {
             if (isDiagnosticsEnabled()) {
-                logDiagnostic("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: [" + trim(e.getMessage()) + "]. Trying alternative implementations...");
+                logDiagnostic("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: [" + e.getMessage().trim() + "]. Trying alternative implementations...");
             }
         }
         catch (final RuntimeException e2) {
             if (isDiagnosticsEnabled()) {
-                logDiagnostic("[LOOKUP] An exception occurred while trying to create an instance of the custom factory class: [" + trim(e2.getMessage()) + "] as specified by a system property.");
+                logDiagnostic("[LOOKUP] An exception occurred while trying to create an instance of the custom factory class: [" + e2.getMessage().trim() + "] as specified by a system property.");
             }
             throw e2;
         }
         if (factory == null) {
             if (isDiagnosticsEnabled()) {
                 logDiagnostic("[LOOKUP] Looking for a resource file of name [META-INF/services/org.apache.commons.logging.LogFactory] to define the LogFactory subclass to use...");
             }
             try {
-                final InputStream is = getResourceAsStream(contextClassLoader, "META-INF/services/org.apache.commons.logging.LogFactory");
-                if (is != null) {
+                final InputStream resourceAsStream = getResourceAsStream(contextClassLoader, "META-INF/services/org.apache.commons.logging.LogFactory");
+                if (resourceAsStream != null) {
                     BufferedReader rd;
                     try {
-                        rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
+                        rd = new BufferedReader(new InputStreamReader(resourceAsStream, "UTF-8"));
                     }
-                    catch (final UnsupportedEncodingException e3) {
-                        rd = new BufferedReader(new InputStreamReader(is));
+                    catch (final UnsupportedEncodingException ex2) {
+                        rd = new BufferedReader(new InputStreamReader(resourceAsStream));
                     }
                     final String factoryClassName = rd.readLine();
                     rd.close();
                     if (factoryClassName != null && !"".equals(factoryClassName)) {
                         if (isDiagnosticsEnabled()) {
                             logDiagnostic("[LOOKUP]  Creating an instance of LogFactory class " + factoryClassName + " as specified by file '" + "META-INF/services/org.apache.commons.logging.LogFactory" + "' which was present in the path of the context" + " classloader.");
                         }
@@ -164,29 +361,29 @@
                 }
                 else if (isDiagnosticsEnabled()) {
                     logDiagnostic("[LOOKUP] No resource file with name 'META-INF/services/org.apache.commons.logging.LogFactory' found.");
                 }
             }
             catch (final Exception ex) {
                 if (isDiagnosticsEnabled()) {
-                    logDiagnostic("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: [" + trim(ex.getMessage()) + "]. Trying alternative implementations...");
+                    logDiagnostic("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: [" + ex.getMessage().trim() + "]. Trying alternative implementations...");
                 }
             }
         }
         if (factory == null) {
             if (props != null) {
                 if (isDiagnosticsEnabled()) {
                     logDiagnostic("[LOOKUP] Looking in properties file for entry with key 'org.apache.commons.logging.LogFactory' to define the LogFactory subclass to use...");
                 }
-                final String factoryClass = props.getProperty("org.apache.commons.logging.LogFactory");
-                if (factoryClass != null) {
+                final String property = props.getProperty("org.apache.commons.logging.LogFactory");
+                if (property != null) {
                     if (isDiagnosticsEnabled()) {
-                        logDiagnostic("[LOOKUP] Properties file specifies LogFactory subclass '" + factoryClass + "'");
+                        logDiagnostic("[LOOKUP] Properties file specifies LogFactory subclass '" + property + "'");
                     }
-                    factory = newFactory(factoryClass, baseClassLoader, contextClassLoader);
+                    factory = newFactory(property, baseClassLoader, contextClassLoader);
                 }
                 else if (isDiagnosticsEnabled()) {
                     logDiagnostic("[LOOKUP] Properties file has no entry specifying LogFactory subclass.");
                 }
             }
             else if (isDiagnosticsEnabled()) {
                 logDiagnostic("[LOOKUP] No properties file available to determine LogFactory subclass from..");
@@ -208,211 +405,39 @@
                     factory.setAttribute(name, value);
                 }
             }
         }
         return factory;
     }
     
+    public abstract Log getInstance(final Class p0) throws LogConfigurationException;
+    
+    public abstract Log getInstance(final String p0) throws LogConfigurationException;
+    
     public static Log getLog(final Class clazz) throws LogConfigurationException {
         return getFactory().getInstance(clazz);
     }
     
     public static Log getLog(final String name) throws LogConfigurationException {
         return getFactory().getInstance(name);
     }
     
-    public static void release(final ClassLoader classLoader) {
-        if (isDiagnosticsEnabled()) {
-            logDiagnostic("Releasing factory for classloader " + objectId((Object)classLoader));
-        }
-        synchronized (LogFactory.factories) {
-            if (classLoader == null) {
-                if (LogFactory.nullClassLoaderFactory != null) {
-                    LogFactory.nullClassLoaderFactory.release();
-                    LogFactory.nullClassLoaderFactory = null;
-                }
-            }
-            else {
-                final LogFactory factory = (LogFactory)LogFactory.factories.get(classLoader);
-                if (factory != null) {
-                    factory.release();
-                    LogFactory.factories.remove(classLoader);
-                }
-            }
-        }
-    }
-    
-    public static void releaseAll() {
-        if (isDiagnosticsEnabled()) {
-            logDiagnostic("Releasing factory for all classloaders.");
-        }
-        synchronized (LogFactory.factories) {
-            final Enumeration elements = LogFactory.factories.elements();
-            while (elements.hasMoreElements()) {
-                final LogFactory element = (LogFactory)elements.nextElement();
-                element.release();
-            }
-            LogFactory.factories.clear();
-            if (LogFactory.nullClassLoaderFactory != null) {
-                LogFactory.nullClassLoaderFactory.release();
-                LogFactory.nullClassLoaderFactory = null;
-            }
-        }
-    }
-    
-    protected static ClassLoader getClassLoader(final Class clazz) {
-        try {
-            return clazz.getClassLoader();
-        }
-        catch (final SecurityException ex) {
-            if (isDiagnosticsEnabled()) {
-                logDiagnostic("Unable to get classloader for class '" + clazz + "' due to security restrictions - " + ex.getMessage());
-            }
-            throw ex;
-        }
-    }
-    
-    protected static ClassLoader getContextClassLoader() throws LogConfigurationException {
-        return directGetContextClassLoader();
-    }
-    
-    private static ClassLoader getContextClassLoaderInternal() throws LogConfigurationException {
-        return AccessController.doPrivileged((PrivilegedAction<ClassLoader>)new LogFactory.LogFactory$1());
-    }
-    
-    protected static ClassLoader directGetContextClassLoader() throws LogConfigurationException {
-        ClassLoader classLoader = null;
-        try {
-            final Method method = Thread.class.getMethod("getContextClassLoader", (Class[])null);
-            try {
-                classLoader = (ClassLoader)method.invoke(Thread.currentThread(), (Object[])null);
-            }
-            catch (final IllegalAccessException e) {
-                throw new LogConfigurationException("Unexpected IllegalAccessException", (Throwable)e);
-            }
-            catch (final InvocationTargetException e2) {
-                if (!(e2.getTargetException() instanceof SecurityException)) {
-                    throw new LogConfigurationException("Unexpected InvocationTargetException", e2.getTargetException());
-                }
-            }
-        }
-        catch (final NoSuchMethodException e3) {
-            classLoader = getClassLoader(LogFactory.class);
-        }
-        return classLoader;
-    }
-    
-    private static LogFactory getCachedFactory(final ClassLoader contextClassLoader) {
-        LogFactory factory = null;
-        if (contextClassLoader == null) {
-            factory = LogFactory.nullClassLoaderFactory;
-        }
-        else {
-            factory = LogFactory.factories.get(contextClassLoader);
-        }
-        return factory;
-    }
-    
-    private static void cacheFactory(final ClassLoader classLoader, final LogFactory factory) {
-        if (factory != null) {
-            if (classLoader == null) {
-                LogFactory.nullClassLoaderFactory = factory;
-            }
-            else {
-                LogFactory.factories.put(classLoader, factory);
-            }
-        }
-    }
-    
-    protected static LogFactory newFactory(final String factoryClass, final ClassLoader classLoader, final ClassLoader contextClassLoader) throws LogConfigurationException {
-        final Object result = AccessController.doPrivileged((PrivilegedAction<Object>)new LogFactory.LogFactory$2(factoryClass, classLoader));
-        if (result instanceof LogConfigurationException) {
-            final LogConfigurationException ex = (LogConfigurationException)result;
-            if (isDiagnosticsEnabled()) {
-                logDiagnostic("An error occurred while loading the factory class:" + ((Throwable)ex).getMessage());
-            }
-            throw ex;
-        }
-        if (isDiagnosticsEnabled()) {
-            logDiagnostic("Created object " + objectId(result) + " to manage classloader " + objectId((Object)contextClassLoader));
-        }
-        return (LogFactory)result;
+    private static Properties getProperties(final URL url) {
+        final PrivilegedAction action = (PrivilegedAction)new LogFactory.LogFactory$5(url);
+        return AccessController.doPrivileged((PrivilegedAction<Properties>)action);
     }
     
-    protected static LogFactory newFactory(final String factoryClass, final ClassLoader classLoader) {
-        return newFactory(factoryClass, classLoader, null);
+    private static InputStream getResourceAsStream(final ClassLoader loader, final String name) {
+        return AccessController.doPrivileged((PrivilegedAction<InputStream>)new LogFactory.LogFactory$3(loader, name));
     }
     
-    protected static Object createFactory(final String factoryClass, final ClassLoader classLoader) {
-        Class logFactoryClass = null;
-        try {
-            if (classLoader != null) {
-                try {
-                    logFactoryClass = classLoader.loadClass(factoryClass);
-                    if (LogFactory.class.isAssignableFrom(logFactoryClass)) {
-                        if (isDiagnosticsEnabled()) {
-                            logDiagnostic("Loaded class " + logFactoryClass.getName() + " from classloader " + objectId((Object)classLoader));
-                        }
-                    }
-                    else if (isDiagnosticsEnabled()) {
-                        logDiagnostic("Factory class " + logFactoryClass.getName() + " loaded from classloader " + objectId((Object)logFactoryClass.getClassLoader()) + " does not extend '" + LogFactory.class.getName() + "' as loaded by this classloader.");
-                        logHierarchy("[BAD CL TREE] ", classLoader);
-                    }
-                    return logFactoryClass.newInstance();
-                }
-                catch (final ClassNotFoundException ex) {
-                    if (classLoader == LogFactory.thisClassLoader) {
-                        if (isDiagnosticsEnabled()) {
-                            logDiagnostic("Unable to locate any class called '" + factoryClass + "' via classloader " + objectId((Object)classLoader));
-                        }
-                        throw ex;
-                    }
-                }
-                catch (final NoClassDefFoundError e) {
-                    if (classLoader == LogFactory.thisClassLoader) {
-                        if (isDiagnosticsEnabled()) {
-                            logDiagnostic("Class '" + factoryClass + "' cannot be loaded" + " via classloader " + objectId((Object)classLoader) + " - it depends on some other class that cannot" + " be found.");
-                        }
-                        throw e;
-                    }
-                }
-                catch (final ClassCastException e2) {
-                    if (classLoader == LogFactory.thisClassLoader) {
-                        final boolean implementsLogFactory = implementsLogFactory(logFactoryClass);
-                        String msg = "The application has specified that a custom LogFactory implementation should be used but Class '" + factoryClass + "' cannot be converted to '" + LogFactory.class.getName() + "'. ";
-                        if (implementsLogFactory) {
-                            msg = msg + "The conflict is caused by the presence of multiple LogFactory classes in incompatible classloaders. " + "Background can be found in http://commons.apache.org/logging/tech.html. " + "If you have not explicitly specified a custom LogFactory then it is likely that " + "the container has set one without your knowledge. " + "In this case, consider using the commons-logging-adapters.jar file or " + "specifying the standard LogFactory from the command line. ";
-                        }
-                        else {
-                            msg += "Please check the custom implementation. ";
-                        }
-                        msg += "Help can be found @http://commons.apache.org/logging/troubleshooting.html.";
-                        if (isDiagnosticsEnabled()) {
-                            logDiagnostic(msg);
-                        }
-                        final ClassCastException ex2 = new ClassCastException(msg);
-                        throw ex2;
-                    }
-                }
-            }
-            if (isDiagnosticsEnabled()) {
-                logDiagnostic("Unable to load factory class via classloader " + objectId((Object)classLoader) + " - trying the classloader associated with this LogFactory.");
-            }
-            logFactoryClass = Class.forName(factoryClass);
-            return logFactoryClass.newInstance();
-        }
-        catch (final Exception e3) {
-            if (isDiagnosticsEnabled()) {
-                logDiagnostic("Unable to create LogFactory instance.");
-            }
-            if (logFactoryClass != null && !LogFactory.class.isAssignableFrom(logFactoryClass)) {
-                return new LogConfigurationException("The chosen LogFactory implementation does not extend LogFactory. Please check your configuration.", (Throwable)e3);
-            }
-            return new LogConfigurationException((Throwable)e3);
-        }
+    private static Enumeration getResources(final ClassLoader loader, final String name) {
+        final PrivilegedAction action = (PrivilegedAction)new LogFactory.LogFactory$4(loader, name);
+        final Object result = AccessController.doPrivileged((PrivilegedAction<Object>)action);
+        return (Enumeration)result;
     }
     
     private static boolean implementsLogFactory(final Class logFactoryClass) {
         boolean implementsLogFactory = false;
         if (logFactoryClass != null) {
             try {
                 final ClassLoader logFactoryClassLoader = logFactoryClass.getClassLoader();
@@ -433,110 +458,25 @@
             }
             catch (final SecurityException e) {
                 logDiagnostic("[CUSTOM LOG FACTORY] SecurityException thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: " + e.getMessage());
             }
             catch (final LinkageError e2) {
                 logDiagnostic("[CUSTOM LOG FACTORY] LinkageError thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: " + e2.getMessage());
             }
-            catch (final ClassNotFoundException e3) {
+            catch (final ClassNotFoundException ex) {
                 logDiagnostic("[CUSTOM LOG FACTORY] LogFactory class cannot be loaded by classloader which loaded the custom LogFactory implementation. Is the custom factory in the right classloader?");
             }
         }
         return implementsLogFactory;
     }
     
-    private static InputStream getResourceAsStream(final ClassLoader loader, final String name) {
-        return AccessController.doPrivileged((PrivilegedAction<InputStream>)new LogFactory.LogFactory$3(loader, name));
-    }
-    
-    private static Enumeration getResources(final ClassLoader loader, final String name) {
-        final PrivilegedAction action = (PrivilegedAction)new LogFactory.LogFactory$4(loader, name);
-        final Object result = AccessController.doPrivileged((PrivilegedAction<Object>)action);
-        return (Enumeration)result;
-    }
-    
-    private static Properties getProperties(final URL url) {
-        final PrivilegedAction action = (PrivilegedAction)new LogFactory.LogFactory$5(url);
-        return AccessController.doPrivileged((PrivilegedAction<Properties>)action);
-    }
-    
-    private static final Properties getConfigurationFile(final ClassLoader classLoader, final String fileName) {
-        Properties props = null;
-        double priority = 0.0;
-        URL propsUrl = null;
-        try {
-            final Enumeration urls = getResources(classLoader, fileName);
-            if (urls == null) {
-                return null;
-            }
-            while (urls.hasMoreElements()) {
-                final URL url = (URL)urls.nextElement();
-                final Properties newProps = getProperties(url);
-                if (newProps != null) {
-                    if (props == null) {
-                        propsUrl = url;
-                        props = newProps;
-                        final String priorityStr = props.getProperty("priority");
-                        priority = 0.0;
-                        if (priorityStr != null) {
-                            priority = Double.parseDouble(priorityStr);
-                        }
-                        if (!isDiagnosticsEnabled()) {
-                            continue;
-                        }
-                        logDiagnostic("[LOOKUP] Properties file found at '" + url + "'" + " with priority " + priority);
-                    }
-                    else {
-                        final String newPriorityStr = newProps.getProperty("priority");
-                        double newPriority = 0.0;
-                        if (newPriorityStr != null) {
-                            newPriority = Double.parseDouble(newPriorityStr);
-                        }
-                        if (newPriority > priority) {
-                            if (isDiagnosticsEnabled()) {
-                                logDiagnostic("[LOOKUP] Properties file at '" + url + "'" + " with priority " + newPriority + " overrides file at '" + propsUrl + "'" + " with priority " + priority);
-                            }
-                            propsUrl = url;
-                            props = newProps;
-                            priority = newPriority;
-                        }
-                        else {
-                            if (!isDiagnosticsEnabled()) {
-                                continue;
-                            }
-                            logDiagnostic("[LOOKUP] Properties file at '" + url + "'" + " with priority " + newPriority + " does not override file at '" + propsUrl + "'" + " with priority " + priority);
-                        }
-                    }
-                }
-            }
-        }
-        catch (final SecurityException e) {
-            if (isDiagnosticsEnabled()) {
-                logDiagnostic("SecurityException thrown while trying to find/read config files.");
-            }
-        }
-        if (isDiagnosticsEnabled()) {
-            if (props == null) {
-                logDiagnostic("[LOOKUP] No properties file of name '" + fileName + "' found.");
-            }
-            else {
-                logDiagnostic("[LOOKUP] Properties file of name '" + fileName + "' found at '" + propsUrl + '\"');
-            }
-        }
-        return props;
-    }
-    
-    private static String getSystemProperty(final String key, final String def) throws SecurityException {
-        return AccessController.doPrivileged((PrivilegedAction<String>)new LogFactory.LogFactory$6(key, def));
-    }
-    
     private static void initDiagnostics() {
         String dest;
         try {
-            dest = getSystemProperty("org.apache.commons.logging.diagnostics.dest", null);
+            dest = System.getProperty("org.apache.commons.logging.diagnostics.dest");
             if (dest == null) {
                 return;
             }
         }
         catch (final SecurityException ex) {
             return;
         }
@@ -551,49 +491,31 @@
                 final FileOutputStream fos = new FileOutputStream(dest, true);
                 LogFactory.diagnosticsStream = new PrintStream(fos);
             }
             catch (final IOException ex2) {
                 return;
             }
         }
-        String classLoaderName;
+        String classLoaderName = null;
         try {
             final ClassLoader classLoader = LogFactory.thisClassLoader;
-            if (LogFactory.thisClassLoader == null) {
-                classLoaderName = "BOOTLOADER";
-            }
-            else {
-                classLoaderName = objectId(classLoader);
+            if (LogFactory.thisClassLoader != null) {
+                objectId(classLoader);
             }
         }
-        catch (final SecurityException e) {
+        catch (final SecurityException ex3) {
             classLoaderName = "UNKNOWN";
         }
         LogFactory.diagnosticPrefix = "[LogFactory from " + classLoaderName + "] ";
     }
     
     protected static boolean isDiagnosticsEnabled() {
         return LogFactory.diagnosticsStream != null;
     }
     
-    private static final void logDiagnostic(final String msg) {
-        if (LogFactory.diagnosticsStream != null) {
-            LogFactory.diagnosticsStream.print(LogFactory.diagnosticPrefix);
-            LogFactory.diagnosticsStream.println(msg);
-            LogFactory.diagnosticsStream.flush();
-        }
-    }
-    
-    protected static final void logRawDiagnostic(final String msg) {
-        if (LogFactory.diagnosticsStream != null) {
-            LogFactory.diagnosticsStream.println(msg);
-            LogFactory.diagnosticsStream.flush();
-        }
-    }
-    
     private static void logClassLoaderEnvironment(final Class clazz) {
         if (!isDiagnosticsEnabled()) {
             return;
         }
         try {
             logDiagnostic("[ENV] Extension directories (java.ext.dir): " + System.getProperty("java.ext.dir"));
             logDiagnostic("[ENV] Application classpath (java.class.path): " + System.getProperty("java.class.path"));
@@ -610,70 +532,138 @@
             logDiagnostic("[ENV] Security forbids determining the classloader for " + className);
             return;
         }
         logDiagnostic("[ENV] Class " + className + " was loaded via classloader " + objectId((Object)classLoader));
         logHierarchy("[ENV] Ancestry of classloader which loaded " + className + " is ", classLoader);
     }
     
+    private static final void logDiagnostic(final String msg) {
+        if (LogFactory.diagnosticsStream != null) {
+            LogFactory.diagnosticsStream.print(LogFactory.diagnosticPrefix);
+            LogFactory.diagnosticsStream.println(msg);
+            LogFactory.diagnosticsStream.flush();
+        }
+    }
+    
     private static void logHierarchy(final String prefix, ClassLoader classLoader) {
         if (!isDiagnosticsEnabled()) {
             return;
         }
         if (classLoader != null) {
             final String classLoaderString = classLoader.toString();
-            logDiagnostic(prefix + objectId((Object)classLoader) + " == '" + classLoaderString + "'");
+            logDiagnostic(String.valueOf(prefix) + objectId((Object)classLoader) + " == '" + classLoaderString + "'");
         }
         ClassLoader systemClassLoader;
         try {
             systemClassLoader = ClassLoader.getSystemClassLoader();
         }
         catch (final SecurityException ex) {
-            logDiagnostic(prefix + "Security forbids determining the system classloader.");
+            logDiagnostic(String.valueOf(prefix) + "Security forbids determining the system classloader.");
             return;
         }
         if (classLoader != null) {
-            final StringBuffer buf = new StringBuffer(prefix + "ClassLoader tree:");
-        Label_0178:
+            final StringBuffer buf = new StringBuffer(String.valueOf(prefix) + "ClassLoader tree:");
+        Label_0177:
             while (true) {
                 do {
                     buf.append(objectId((Object)classLoader));
                     if (classLoader == systemClassLoader) {
                         buf.append(" (SYSTEM) ");
                     }
                     try {
                         classLoader = classLoader.getParent();
                     }
                     catch (final SecurityException ex2) {
                         buf.append(" --> SECRET");
-                        break Label_0178;
+                        break Label_0177;
                     }
                     buf.append(" --> ");
                     continue;
                     logDiagnostic(buf.toString());
                     return;
                 } while (classLoader != null);
                 buf.append("BOOT");
-                continue Label_0178;
+                continue Label_0177;
             }
         }
     }
     
+    protected static final void logRawDiagnostic(final String msg) {
+        if (LogFactory.diagnosticsStream != null) {
+            LogFactory.diagnosticsStream.println(msg);
+            LogFactory.diagnosticsStream.flush();
+        }
+    }
+    
+    protected static LogFactory newFactory(final String factoryClass, final ClassLoader classLoader) {
+        return newFactory(factoryClass, classLoader, null);
+    }
+    
+    protected static LogFactory newFactory(final String factoryClass, final ClassLoader classLoader, final ClassLoader contextClassLoader) throws LogConfigurationException {
+        final Object result = AccessController.doPrivileged((PrivilegedAction<Object>)new LogFactory.LogFactory$2(classLoader, factoryClass));
+        if (result instanceof LogConfigurationException) {
+            final LogConfigurationException ex = (LogConfigurationException)result;
+            if (isDiagnosticsEnabled()) {
+                logDiagnostic("An error occurred while loading the factory class:" + ((Throwable)ex).getMessage());
+            }
+            throw ex;
+        }
+        if (isDiagnosticsEnabled()) {
+            logDiagnostic("Created object " + objectId(result) + " to manage classloader " + objectId((Object)contextClassLoader));
+        }
+        return (LogFactory)result;
+    }
+    
     public static String objectId(final Object o) {
         if (o == null) {
             return "null";
         }
-        return o.getClass().getName() + "@" + System.identityHashCode(o);
+        return String.valueOf(o.getClass().getName()) + "@" + System.identityHashCode(o);
     }
     
-    static {
-        LogFactory.diagnosticsStream = null;
-        LogFactory.factories = null;
-        LogFactory.nullClassLoaderFactory = null;
-        LogFactory.thisClassLoader = getClassLoader(LogFactory.class);
-        initDiagnostics();
-        logClassLoaderEnvironment(LogFactory.class);
-        LogFactory.factories = createFactoryStore();
+    public abstract void release();
+    
+    public static void release(final ClassLoader classLoader) {
         if (isDiagnosticsEnabled()) {
-            logDiagnostic("BOOTSTRAP COMPLETED");
+            logDiagnostic("Releasing factory for classloader " + objectId((Object)classLoader));
+        }
+        synchronized (LogFactory.factories) {
+            if (classLoader == null) {
+                if (LogFactory.nullClassLoaderFactory != null) {
+                    LogFactory.nullClassLoaderFactory.release();
+                    LogFactory.nullClassLoaderFactory = null;
+                }
+            }
+            else {
+                final LogFactory factory = (LogFactory)LogFactory.factories.get(classLoader);
+                if (factory != null) {
+                    factory.release();
+                    LogFactory.factories.remove(classLoader);
+                }
+            }
+            monitorexit(LogFactory.factories);
+        }
+    }
+    
+    public static void releaseAll() {
+        if (isDiagnosticsEnabled()) {
+            logDiagnostic("Releasing factory for all classloaders.");
+        }
+        synchronized (LogFactory.factories) {
+            final Enumeration elements = LogFactory.factories.elements();
+            while (elements.hasMoreElements()) {
+                final LogFactory element = (LogFactory)elements.nextElement();
+                element.release();
+            }
+            LogFactory.factories.clear();
+            if (LogFactory.nullClassLoaderFactory != null) {
+                LogFactory.nullClassLoaderFactory.release();
+                LogFactory.nullClassLoaderFactory = null;
+            }
+            monitorexit(LogFactory.factories);
         }
     }
+    
+    public abstract void removeAttribute(final String p0);
+    
+    public abstract void setAttribute(final String p0, final Object p1);
 }

```

```diff
@@ -19,15 +19,15 @@
     
     public WeakHashtable() {
         this.queue = new ReferenceQueue();
         this.changeCount = 0;
     }
     
     public boolean containsKey(final Object key) {
-        final WeakHashtable.Referenced referenced = new WeakHashtable.Referenced(key, (WeakHashtable.WeakHashtable$1)null);
+        final WeakHashtable.Referenced referenced = new WeakHashtable.Referenced((WeakHashtable.2)null, key);
         return super.containsKey(referenced);
     }
     
     public Enumeration elements() {
         this.purge();
         return super.elements();
     }
@@ -36,126 +36,129 @@
         this.purge();
         final Set referencedEntries = super.entrySet();
         final Set unreferencedEntries = new HashSet();
         final Iterator it = referencedEntries.iterator();
         while (it.hasNext()) {
             final Map.Entry entry = (Map.Entry)it.next();
             final WeakHashtable.Referenced referencedKey = (WeakHashtable.Referenced)entry.getKey();
-            final Object key = WeakHashtable.Referenced.access$100(referencedKey);
+            final Object key = WeakHashtable.Referenced.access$0(referencedKey);
             final Object value = entry.getValue();
             if (key != null) {
-                final WeakHashtable.Entry dereferencedEntry = new WeakHashtable.Entry(key, value, (WeakHashtable.WeakHashtable$1)null);
+                final WeakHashtable.Entry dereferencedEntry = new WeakHashtable.Entry((WeakHashtable.2)null, key, value);
                 unreferencedEntries.add(dereferencedEntry);
             }
         }
         return unreferencedEntries;
     }
     
     public Object get(final Object key) {
-        final WeakHashtable.Referenced referenceKey = new WeakHashtable.Referenced(key, (WeakHashtable.WeakHashtable$1)null);
+        final WeakHashtable.Referenced referenceKey = new WeakHashtable.Referenced((WeakHashtable.2)null, key);
         return super.get(referenceKey);
     }
     
-    public Enumeration keys() {
+    public boolean isEmpty() {
         this.purge();
-        final Enumeration enumer = super.keys();
-        return (Enumeration)new WeakHashtable.WeakHashtable$1(this, enumer);
+        return super.isEmpty();
     }
     
     public Set keySet() {
         this.purge();
         final Set referencedKeys = super.keySet();
         final Set unreferencedKeys = new HashSet();
         final Iterator it = referencedKeys.iterator();
         while (it.hasNext()) {
             final WeakHashtable.Referenced referenceKey = (WeakHashtable.Referenced)it.next();
-            final Object keyValue = WeakHashtable.Referenced.access$100(referenceKey);
+            final Object keyValue = WeakHashtable.Referenced.access$0(referenceKey);
             if (keyValue != null) {
                 unreferencedKeys.add(keyValue);
             }
         }
         return unreferencedKeys;
     }
     
+    public Enumeration keys() {
+        this.purge();
+        final Enumeration enumer = super.keys();
+        return (Enumeration)new WeakHashtable.WeakHashtable$1(enumer);
+    }
+    
+    private void purge() {
+        synchronized (this.queue) {
+            WeakHashtable.WeakKey key;
+            while ((key = (WeakHashtable.WeakKey)this.queue.poll()) != null) {
+                super.remove(WeakHashtable.WeakKey.access$0(key));
+            }
+            monitorexit(this.queue);
+        }
+    }
+    
+    private void purgeOne() {
+        synchronized (this.queue) {
+            final WeakHashtable.WeakKey key = (WeakHashtable.WeakKey)this.queue.poll();
+            if (key != null) {
+                super.remove(WeakHashtable.WeakKey.access$0(key));
+            }
+            monitorexit(this.queue);
+        }
+    }
+    
     public Object put(final Object key, final Object value) {
         if (key == null) {
             throw new NullPointerException("Null keys are not allowed");
         }
         if (value == null) {
             throw new NullPointerException("Null values are not allowed");
         }
         if (this.changeCount++ > 100) {
             this.purge();
             this.changeCount = 0;
         }
         else if (this.changeCount % 10 == 0) {
             this.purgeOne();
         }
-        final WeakHashtable.Referenced keyRef = new WeakHashtable.Referenced(key, this.queue, (WeakHashtable.WeakHashtable$1)null);
+        final Object result = null;
+        final WeakHashtable.Referenced keyRef = new WeakHashtable.Referenced((WeakHashtable.2)null, key, this.queue);
         return super.put(keyRef, value);
     }
     
     public void putAll(final Map t) {
         if (t != null) {
             final Set entrySet = t.entrySet();
             final Iterator it = entrySet.iterator();
             while (it.hasNext()) {
                 final Map.Entry entry = (Map.Entry)it.next();
                 this.put(entry.getKey(), entry.getValue());
             }
         }
     }
     
-    public Collection values() {
+    protected void rehash() {
         this.purge();
-        return super.values();
+        super.rehash();
     }
     
     public Object remove(final Object key) {
         if (this.changeCount++ > 100) {
             this.purge();
             this.changeCount = 0;
         }
         else if (this.changeCount % 10 == 0) {
             this.purgeOne();
         }
-        return super.remove(new WeakHashtable.Referenced(key, (WeakHashtable.WeakHashtable$1)null));
-    }
-    
-    public boolean isEmpty() {
-        this.purge();
-        return super.isEmpty();
+        return super.remove(new WeakHashtable.Referenced((WeakHashtable.2)null, key));
     }
     
     public int size() {
         this.purge();
         return super.size();
     }
     
     public String toString() {
         this.purge();
         return super.toString();
     }
     
-    protected void rehash() {
+    public Collection values() {
         this.purge();
-        super.rehash();
-    }
-    
-    private void purge() {
-        synchronized (this.queue) {
-            WeakHashtable.WeakKey key;
-            while ((key = (WeakHashtable.WeakKey)this.queue.poll()) != null) {
-                super.remove(WeakHashtable.WeakKey.access$400(key));
-            }
-        }
-    }
-    
-    private void purgeOne() {
-        synchronized (this.queue) {
-            final WeakHashtable.WeakKey key = (WeakHashtable.WeakKey)this.queue.poll();
-            if (key != null) {
-                super.remove(WeakHashtable.WeakKey.access$400(key));
-            }
-        }
+        return super.values();
     }
 }

```

## com.github.ldapchai:ldapchai:0.8.6

Diffoscope JSON file: [com.github.ldapchai_ldapchai_0.8.6.diffoscope.json](com.github.ldapchai_ldapchai_0.8.6.diffoscope.json)

```diff
@@ -82,20 +82,20 @@
         try {
             md = MessageDigest.getInstance(hashType.getHashAlgName());
         }
         catch (final NoSuchAlgorithmException e) {
             throw new IllegalStateException("unable to load " + hashType.getHashAlgName() + " message digest algorithm: " + e.getMessage());
         }
         byte[] hashedBytes = input.getBytes(ChaiCrFactory.DEFAULT_CHARSET);
-        switch (version.ordinal()) {
-            case 0: {
+        switch (HashSaltAnswer.HashSaltAnswer$1.$SwitchMap$com$novell$ldapchai$cr$HashSaltAnswer$VERSION[version.ordinal()]) {
+            case 1: {
                 hashedBytes = md.digest(hashedBytes);
                 return StringHelper.base64Encode(hashedBytes, new StringHelper.Base64Options[0]);
             }
-            case 1: {
+            case 2: {
                 for (int i = 0; i < hashCount; ++i) {
                     hashedBytes = md.digest(hashedBytes);
                 }
                 return StringHelper.base64Encode(hashedBytes, new StringHelper.Base64Options[0]);
             }
             default: {
                 throw new IllegalStateException("unexpected version enum in hash method");

```

```diff
@@ -113,15 +113,15 @@
     }
     
     void reportBrokenProvider(final ChaiProvider provider, final Exception e) {
         if (this.failState != FailOverRotationMachine.FailState.OKAY) {
             return;
         }
         final ChaiProvider presumedCurrentProvider = (ChaiProvider)((FailOverRotationMachine.ProviderSlot)this.providerSlots.get(this.activeSlot.get())).getProvider();
-        if (presumedCurrentProvider != null && presumedCurrentProvider.equals((Object)provider)) {
+        if (presumedCurrentProvider != null && presumedCurrentProvider.equals(provider)) {
             this.currentServerIsBroken(e);
         }
     }
     
     private void failbackCheck() {
         if (this.failState == FailOverRotationMachine.FailState.OKAY && this.activeSlot.get() != 0) {
             final Duration msSinceLastFailure = Duration.between(this.lastFailureTime, Instant.now());

```

```diff
@@ -51,15 +51,15 @@
             return true;
         }
         if (!(o instanceof ChaiEntry)) {
             return false;
         }
         final AbstractChaiEntry chaiEntry = (AbstractChaiEntry)o;
         if (this.chaiProvider != null) {
-            if (!this.chaiProvider.equals((Object)chaiEntry.chaiProvider)) {
+            if (!this.chaiProvider.equals(chaiEntry.chaiProvider)) {
                 return false;
             }
         }
         else if (chaiEntry.chaiProvider != null) {
             return false;
         }
         if (this.entryDN != null) {
@@ -206,16 +206,16 @@
         int i = 0;
         while (i < length) {
             final byte[] value = array[i];
             final String strValue = new String(value, Charset.forName(characterEncoding));
             final int sepPos = strValue.indexOf(35);
             final int typeInt = Integer.parseInt(strValue.substring(0, sepPos));
             final AbstractChaiEntry.NetworkAddressType type = AbstractChaiEntry.NetworkAddressType.forIdentifier(typeInt);
-            switch (type.ordinal()) {
-                case 0: {
+            switch (AbstractChaiEntry.AbstractChaiEntry$1.$SwitchMap$com$novell$ldapchai$impl$AbstractChaiEntry$NetworkAddressType[type.ordinal()]) {
+                case 1: {
                     final StringBuilder sb = new StringBuilder();
                     try {
                         sb.append(256 + value[sepPos + 3] % 256).append(".");
                         sb.append(256 + value[sepPos + 4] % 256).append(".");
                         sb.append(256 + value[sepPos + 5] % 256).append(".");
                         sb.append(256 + value[sepPos + 6] % 256);
                         returnValues.add(InetAddress.getByName(sb.toString()));

```

## org.apache.bcel:bcel:6.10.0

Diffoscope JSON file: [org.apache.bcel_bcel_6.10.0.diffoscope.json](org.apache.bcel_bcel_6.10.0.diffoscope.json)

```diff
@@ -1,17 +1,31 @@
 
 package org.apache.bcel.data;
 
 import java.lang.reflect.Constructor;
 
 public class SWAP
 {
+    static /* synthetic */ Class class$0;
+    
     public static Constructor getTestConstructor(final Class theClass) throws NoSuchMethodException {
-        final Class[] args = { String.class };
+        final Class[] array = { null };
+        final int n = 0;
+        Class class$0;
+        if ((class$0 = SWAP.class$0) == null) {
+            try {
+                class$0 = (SWAP.class$0 = Class.forName("java.lang.String"));
+            }
+            catch (final ClassNotFoundException ex) {
+                throw new NoClassDefFoundError(ex.getMessage());
+            }
+        }
+        array[n] = class$0;
+        final Class[] args = array;
         try {
             return theClass.getConstructor((Class[])args);
         }
-        catch (final NoSuchMethodException ex) {
+        catch (final NoSuchMethodException ex2) {
             return theClass.getConstructor((Class[])new Class[0]);
         }
     }
 }

```

## fr.vidal.oss:atom-jaxb:1.1.0

Diffoscope JSON file: [fr.vidal.oss_atom-jaxb_1.1.0.diffoscope.json](fr.vidal.oss_atom-jaxb_1.1.0.diffoscope.json)

```diff
@@ -23,18 +23,14 @@
 import fr.vidal.oss.jaxb.atom.core.LinkAssert;
 import fr.vidal.oss.jaxb.atom.core.Link;
 import fr.vidal.oss.jaxb.atom.core.FeedBuilderAssert;
 import fr.vidal.oss.jaxb.atom.core.FeedAssert;
 import fr.vidal.oss.jaxb.atom.core.Feed;
 import fr.vidal.oss.jaxb.atom.core.ExtensionElementsAssert;
 import fr.vidal.oss.jaxb.atom.core.ExtensionElements;
-import fr.vidal.oss.jaxb.atom.core.ExtensionElementTestAssert;
-import fr.vidal.oss.jaxb.atom.core.ExtensionElementTest;
-import fr.vidal.oss.jaxb.atom.core.ExtensionElementAdapterTestAssert;
-import fr.vidal.oss.jaxb.atom.core.ExtensionElementAdapterTest;
 import fr.vidal.oss.jaxb.atom.core.ExtensionElementAdapterAssert;
 import fr.vidal.oss.jaxb.atom.core.ExtensionElementAdapter;
 import fr.vidal.oss.jaxb.atom.core.ExtensionElementAssert;
 import fr.vidal.oss.jaxb.atom.core.ExtensionElement;
 import fr.vidal.oss.jaxb.atom.core.EntryBuilderAssert;
 import fr.vidal.oss.jaxb.atom.core.EntryAssert;
 import fr.vidal.oss.jaxb.atom.core.Entry;
@@ -158,24 +154,14 @@
     
     @CheckReturnValue
     public ExtensionElementAdapterAssert assertThat(final ExtensionElementAdapter actual) {
         return (ExtensionElementAdapterAssert)this.proxy((Class)ExtensionElementAdapterAssert.class, (Class)ExtensionElementAdapter.class, (Object)actual);
     }
     
     @CheckReturnValue
-    public ExtensionElementAdapterTestAssert assertThat(final ExtensionElementAdapterTest actual) {
-        return (ExtensionElementAdapterTestAssert)this.proxy((Class)ExtensionElementAdapterTestAssert.class, (Class)ExtensionElementAdapterTest.class, (Object)actual);
-    }
-    
-    @CheckReturnValue
-    public ExtensionElementTestAssert assertThat(final ExtensionElementTest actual) {
-        return (ExtensionElementTestAssert)this.proxy((Class)ExtensionElementTestAssert.class, (Class)ExtensionElementTest.class, (Object)actual);
-    }
-    
-    @CheckReturnValue
     public ExtensionElementsAssert assertThat(final ExtensionElements actual) {
         return (ExtensionElementsAssert)this.proxy((Class)ExtensionElementsAssert.class, (Class)ExtensionElements.class, (Object)actual);
     }
     
     @CheckReturnValue
     public FeedAssert assertThat(final Feed actual) {
         return (FeedAssert)this.proxy((Class)FeedAssert.class, (Class)Feed.class, (Object)actual);

```

```diff
@@ -23,18 +23,14 @@
 import fr.vidal.oss.jaxb.atom.core.LinkAssert;
 import fr.vidal.oss.jaxb.atom.core.Link;
 import fr.vidal.oss.jaxb.atom.core.FeedBuilderAssert;
 import fr.vidal.oss.jaxb.atom.core.FeedAssert;
 import fr.vidal.oss.jaxb.atom.core.Feed;
 import fr.vidal.oss.jaxb.atom.core.ExtensionElementsAssert;
 import fr.vidal.oss.jaxb.atom.core.ExtensionElements;
-import fr.vidal.oss.jaxb.atom.core.ExtensionElementTestAssert;
-import fr.vidal.oss.jaxb.atom.core.ExtensionElementTest;
-import fr.vidal.oss.jaxb.atom.core.ExtensionElementAdapterTestAssert;
-import fr.vidal.oss.jaxb.atom.core.ExtensionElementAdapterTest;
 import fr.vidal.oss.jaxb.atom.core.ExtensionElementAdapterAssert;
 import fr.vidal.oss.jaxb.atom.core.ExtensionElementAdapter;
 import fr.vidal.oss.jaxb.atom.core.ExtensionElementAssert;
 import fr.vidal.oss.jaxb.atom.core.ExtensionElement;
 import fr.vidal.oss.jaxb.atom.core.EntryBuilderAssert;
 import fr.vidal.oss.jaxb.atom.core.EntryAssert;
 import fr.vidal.oss.jaxb.atom.core.Entry;
@@ -158,24 +154,14 @@
     
     @CheckReturnValue
     public static ExtensionElementAdapterAssert then(final ExtensionElementAdapter actual) {
         return new ExtensionElementAdapterAssert(actual);
     }
     
     @CheckReturnValue
-    public static ExtensionElementAdapterTestAssert then(final ExtensionElementAdapterTest actual) {
-        return new ExtensionElementAdapterTestAssert(actual);
-    }
-    
-    @CheckReturnValue
-    public static ExtensionElementTestAssert then(final ExtensionElementTest actual) {
-        return new ExtensionElementTestAssert(actual);
-    }
-    
-    @CheckReturnValue
     public static ExtensionElementsAssert then(final ExtensionElements actual) {
         return new ExtensionElementsAssert(actual);
     }
     
     @CheckReturnValue
     public static FeedAssert then(final Feed actual) {
         return new FeedAssert(actual);

```

```diff
@@ -23,18 +23,14 @@
 import fr.vidal.oss.jaxb.atom.core.LinkAssert;
 import fr.vidal.oss.jaxb.atom.core.Link;
 import fr.vidal.oss.jaxb.atom.core.FeedBuilderAssert;
 import fr.vidal.oss.jaxb.atom.core.FeedAssert;
 import fr.vidal.oss.jaxb.atom.core.Feed;
 import fr.vidal.oss.jaxb.atom.core.ExtensionElementsAssert;
 import fr.vidal.oss.jaxb.atom.core.ExtensionElements;
-import fr.vidal.oss.jaxb.atom.core.ExtensionElementTestAssert;
-import fr.vidal.oss.jaxb.atom.core.ExtensionElementTest;
-import fr.vidal.oss.jaxb.atom.core.ExtensionElementAdapterTestAssert;
-import fr.vidal.oss.jaxb.atom.core.ExtensionElementAdapterTest;
 import fr.vidal.oss.jaxb.atom.core.ExtensionElementAdapterAssert;
 import fr.vidal.oss.jaxb.atom.core.ExtensionElementAdapter;
 import fr.vidal.oss.jaxb.atom.core.ExtensionElementAssert;
 import fr.vidal.oss.jaxb.atom.core.ExtensionElement;
 import fr.vidal.oss.jaxb.atom.core.EntryBuilderAssert;
 import fr.vidal.oss.jaxb.atom.core.EntryAssert;
 import fr.vidal.oss.jaxb.atom.core.Entry;
@@ -158,24 +154,14 @@
     
     @CheckReturnValue
     public static ExtensionElementAdapterAssert assertThat(final ExtensionElementAdapter actual) {
         return new ExtensionElementAdapterAssert(actual);
     }
     
     @CheckReturnValue
-    public static ExtensionElementAdapterTestAssert assertThat(final ExtensionElementAdapterTest actual) {
-        return new ExtensionElementAdapterTestAssert(actual);
-    }
-    
-    @CheckReturnValue
-    public static ExtensionElementTestAssert assertThat(final ExtensionElementTest actual) {
-        return new ExtensionElementTestAssert(actual);
-    }
-    
-    @CheckReturnValue
     public static ExtensionElementsAssert assertThat(final ExtensionElements actual) {
         return new ExtensionElementsAssert(actual);
     }
     
     @CheckReturnValue
     public static FeedAssert assertThat(final Feed actual) {
         return new FeedAssert(actual);

```

## org.apache.sling:org.apache.sling.feature.extension.unpack:0.4.0

Diffoscope JSON file: [org.apache.sling_org.apache.sling.feature.extension.unpack_0.4.0.diffoscope.json](org.apache.sling_org.apache.sling.feature.extension.unpack_0.4.0.diffoscope.json)

```diff
@@ -1,12 +1,12 @@
 
 package org.osgi.util.converter;
 
 import java.lang.reflect.Type;
 import java.lang.reflect.GenericArrayType;
 
-class ConvertingImpl$2 implements GenericArrayType {
+static final class ConvertingImpl$2 implements GenericArrayType {
     @Override
     public Type getGenericComponentType() {
         return this.val$reifiedType;
     }
 }

```

```diff
@@ -1,15 +1,15 @@
 
 package org.osgi.util.converter;
 
 import java.util.Arrays;
 import java.lang.reflect.Type;
 import java.lang.reflect.ParameterizedType;
 
-class ConvertingImpl$1 implements ParameterizedType {
+static final class ConvertingImpl$1 implements ParameterizedType {
     @Override
     public Type getRawType() {
         return this.val$parameterizedType.getRawType();
     }
     
     @Override
     public Type getOwnerType() {

```

```diff
@@ -1,14 +1,14 @@
 
 package org.osgi.util.converter;
 
 import org.osgi.util.function.Function;
 import java.lang.reflect.Type;
 
-class TypeRule$1 implements ConverterFunction {
+static final class TypeRule$1 implements ConverterFunction {
     public Object apply(final Object obj, final Type targetType) throws Exception {
         if (this.val$from instanceof Class) {
             final Class<?> cls = (Class)this.val$from;
             if (cls.isInstance(obj)) {
                 final Object res = this.val$func.apply(obj);
                 if (res != null) {
                     return res;

```

```diff
@@ -755,15 +755,15 @@
     private Collection<?> asCollection(final InternalConverter converter) {
         if (this.object instanceof Collection) {
             return (Collection)this.object;
         }
         final Object boxedArray = asBoxedArray(this.object);
         this.object = boxedArray;
         if (boxedArray instanceof Object[]) {
-            return Arrays.asList((Object[])this.object);
+            return Arrays.asList((Object[])(Object[])this.object);
         }
         if (isMapType(this.sourceClass, this.sourceAsJavaBean, this.sourceAsDTO)) {
             return this.mapView(this.object, this.sourceClass, converter).entrySet();
         }
         return null;
     }
     

```

### dev.langchain4j:langchain4j:0.27.1

Diffoscope JSON file: [dev.langchain4j_langchain4j_0.27.1.diffoscope.json](langchain4j-infinispan-0.27.1.jar.diffoscope.json)

```diff
@@ -39,15 +39,15 @@
     
     public List<String> getMetadataValues() {
         return this.metadataValues;
     }
     
     @Override
     public String toString() {
-        return "LangchainInfinispanItem{id='" + this.id + "', embedding=" + Arrays.toString(this.embedding) + ", text='" + this.text + "', metadataKeys=" + String.valueOf((Object)this.metadataKeys) + ", metadataValues=" + String.valueOf((Object)this.metadataValues);
+        return "LangchainInfinispanItem{id='" + this.id + "', embedding=" + Arrays.toString(this.embedding) + ", text='" + this.text + "', metadataKeys=" + this.metadataKeys + ", metadataValues=" + this.metadataValues;
     }
     
     @Override
     public boolean equals(final Object o) {
         if (this == o) {
             return true;
         }

```
