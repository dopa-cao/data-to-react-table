package cn.com.clm.util;

import cn.com.clm.anno.Table;
import cn.com.clm.constant.Constant;
import cn.com.clm.error.ErrorEnum;
import cn.com.clm.exception.CommonException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.util.privilegedactions.SetContextClassLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static cn.com.clm.constant.Constant.CLASS_SUFFIX;
import static cn.com.clm.constant.Constant.JAR_SUFFEIX;

/**
 * describe: scan util
 *
 * @author liming.cao@hand-china.com
 */
public class ScanUtil {


    public static Map<String, Class> getClsMap(String packagePath) throws CommonException {
        Map<String, Class> map = new HashMap<>();
        Set<String> classNames = scanPackage(packagePath);
       try {
           for (String className : classNames) {
               Class cls = Class.forName(className);
               Table table = (Table) cls.getAnnotation(Table.class);
               if (null != table) {
                   map.put(table.type(), cls);
               }
           }
       } catch (ClassNotFoundException e) {
            throw new CommonException(ErrorEnum.IMPORT_ERROR);
       }
       return map;
    }

    private static Set<String> scanPackage(String packageName) throws CommonException {
        Set<String> set = null;
        String path = packageName.replace(".", "/");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource(path);
        if (null != url) {
            String protocol = url.getProtocol();
            if (StringUtils.equals(protocol, Constant.TYPE_FILE)) {
                set = getClassFromDir(url.getPath(), packageName);
            } else if (StringUtils.equals(protocol, Constant.TYPE_JAR)){
                try (JarFile jarFile = ((JarURLConnection)url.openConnection()).getJarFile()){
                    set = getClassFromJar(jarFile.entries(), packageName);
                }catch (IOException e){
                    throw new CommonException(ErrorEnum.IMPORT_ERROR);
                }
            }
        }else {
            set = getClassFromJars(((URLClassLoader)classLoader).getURLs(), packageName);
        }
        return set;
    }

    private static Set<String> getClassFromJar(Enumeration<JarEntry> entries, String packageName) {
        Set<String> set = new HashSet<>();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            if (!jarEntry.isDirectory()) {
                String entryName = jarEntry.getName().replace("/", ".");
                if (StringUtils.endsWith(entryName, CLASS_SUFFIX) &&
                        !StringUtils.contains(entryName, "$") &&
                        StringUtils.startsWith(entryName, packageName)) {
                    set.add(entryName.replace(CLASS_SUFFIX, ""));
                }
            }
        }
        return set;
    }

    private static Set<String> getClassFromJars(URL[] urLs, String packageName) throws CommonException {
        Set<String> set = new HashSet<>();
        for (URL urL : urLs) {
            String path = urL.getPath();
            if (StringUtils.endsWith(path, JAR_SUFFEIX)) {
                continue;
            } try (JarFile jarFile = new JarFile(StringUtils.substring(path, path.indexOf("/")))){
                set.addAll(getClassFromJar(jarFile.entries(), packageName));
            }catch (IOException e) {
                throw new CommonException(ErrorEnum.IMPORT_ERROR);
            }
        }
        return set;
    }

    private static Set<String> getClassFromDir(String path, String packageName) {
        Set<String> set = new HashSet<>();
        File file = new File(path);
        File[] files = file.listFiles();
        if (null == files) {
            return set;
        }
        for (File childFile : files) {
            if (childFile.isFile()) {
                String fileName = childFile.getName();
                if (StringUtils.endsWith(fileName, CLASS_SUFFIX) && !StringUtils.contains(fileName,"$")) {
                    set.add(packageName + "." + fileName.replace(CLASS_SUFFIX, ""));
                }
            } else {
                set.addAll(getClassFromDir(childFile.getPath(), packageName + "." + childFile.getName()));
            }
        }
        return set;
    }

}
