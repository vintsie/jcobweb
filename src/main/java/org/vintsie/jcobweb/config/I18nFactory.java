package org.vintsie.jcobweb.config;

import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Create the message string.
 * <p/>
 * Created by dev001 on 1/7/14.
 */
public class I18nFactory {

    private transient static Log log = LogFactory.getLog(I18nFactory.class);

    private static Properties i18n_dic;

    static {
        // load i18n information
        loadI18nDictionary(SystemInfo.getLanguage());
    }

    /**
     * get i18n information from memory by key
     *
     * @param key the properties file's key
     * @return i18n message or template
     */
    public static String getI18nInfo(String key) {

        if (StringUtils.isEmpty(key))
            return StringUtils.EMPTY;

        return i18n_dic.getProperty(key, key);
    }

    /**
     * get i18n information replacing the indexed param.
     *
     * @param key    i18n message key
     * @param params indexed variable to replaced.
     * @return i18n message has been replaced
     */
    public static String getI18nInfo(String key, String... params) {
        String i18n_template = getI18nInfo(key);
        for (int i = 0; i < params.length; i++) {
            i18n_template = i18n_template.replace("{" + i + "}", params[i]);
        }
        return i18n_template;
    }

    /**
     * load properties file into memory variable
     *
     * @param cp          properties file's context path
     * @param charsetName Charset
     * @return {@link java.util.Properties}
     * @throws java.io.IOException
     */
    public static Properties loadProperties(String cp, String charsetName) throws IOException {
        Properties properties = new Properties();
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(
                    I18nFactory.class.getResourceAsStream(cp),
                    charsetName);
            properties.load(isr);
        } catch (IOException ioe) {
            log.error("Failed in loading property file [" + cp + "].", ioe);
        } finally {
            if (null != isr) {
                isr.close();
            }
        }
        return properties;
    }

    /**
     * read i18n message template information.
     *
     * @param locale language location
     */
    public static void loadI18nDictionary(String locale) {
        try {
            if (log.isInfoEnabled()) {
                log.info("Starts to load i18n message of " + locale);
            }
            i18n_dic =
                    loadProperties("/i18n/jcobweb_i18n_" + locale + ".properties", CharEncoding.UTF_8);

            if (log.isInfoEnabled()) {
                log.info(i18n_dic.size() + " counts i18n message(s) of " + locale + " loaded.");
            }
        } catch (IOException ioe) {
            log.error("Failed in reading i18n property file, the i18n message dictionary will be empty.", ioe);
        }
    }
}
