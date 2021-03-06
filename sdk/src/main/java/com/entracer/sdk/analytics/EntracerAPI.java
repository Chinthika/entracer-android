package com.entracer.sdk.analytics;

import com.entracer.sdk.model.Organisation;
import com.entracer.sdk.model.Person;
import com.entracer.sdk.network.HttpService;
import com.entracer.sdk.network.Request;
import com.entracer.sdk.network.RequestMethod;
import com.entracer.sdk.network.ResponseListener;
import com.entracer.sdk.util.Constants;
import com.entracer.sdk.util.EntracerLog;

import java.util.HashMap;
import java.util.Map;

/**
 * Main class for interacting with Entracer api.
 */
public class EntracerAPI {

    /**
     * Singleton entracer api instance.
     */
    private static EntracerAPI instance = new EntracerAPI();

    private String mToken;
    private String mPersonID;
    private String mOrganisationID;

    /**
     * Private constructor to make instantiating directly prohibited.
     */
    private EntracerAPI() {
        this.mToken = null;
        this.mPersonID = null;
        this.mOrganisationID = null;
    }

    /**
     * Returns Entracer singleton api instance for tracking.
     * @param token api token.
     * @return Entracer instance.
     */
    public static EntracerAPI getInstance(String token) {
        if (null == token) {
            return null;
        }

        instance.mToken = token;
        return instance;
    }

    /**
     * Returns Entracer singleton api instance if token is set.
     * @return Entracer instance or null.
     */
    public static EntracerAPI getInstance() {

        if (null == instance.mToken) {
            return null;
        }

        return instance;
    }

    /**
     * Sets Entracer log levels. Log levels could be DEBUG, INFO, WARN, ERROR, NONE
     * @param level EntracerLog level.
     */
    public void setLogLevel(int level) {
        EntracerLog.setLevel(level);
    }

    /**
     * Resets Entracer api values.
     */
    public void reset() {
        this.mToken = null;
        this.mPersonID = null;
        this.mOrganisationID = null;
    }

    /**
     * Returns api token for entracer.
     * @return token.
     */
    public String getToken() {
        return this.mToken;
    }

    public void trigger(String event, String personID, String organisationID, String channel) {

    }

    /**
     * Sends create or update person API request and notify the response listener.
     * @param person person instance.
     * @param listener response listener.
     * @throws Exception exception.
     */
    public void createOrUpdate(Person person, ResponseListener listener) throws Exception {

        String base = Constants.API.BASE_PATH;
        String path = Constants.API.VERSION + Constants.EndPoints.PEOPLE + Constants.EndPaths.CREATE_OR_UPDATE;

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("person", person.data);

        Request createPersonRequest = new Request(mToken, base, path, "", RequestMethod.POST, data);
        HttpService service = new HttpService(createPersonRequest, listener);
        service.sendRequest();
    }

    /**
     * Sends create or update organisation API request and notify the response listener.
     * @param organisation
     * @param listener
     * @throws Exception exception.
     */
    public void createOrUpdate(Organisation organisation, ResponseListener listener) throws Exception {

        String base = Constants.API.BASE_PATH;
        String path = Constants.API.VERSION + Constants.EndPoints.ORGANISATIONS + Constants.EndPaths.CREATE_OR_UPDATE;

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("organisation", organisation.data);

        Request createOrganisationRequest = new Request(mToken, base, path, "", RequestMethod.POST, data);
        HttpService service = new HttpService(createOrganisationRequest, listener);
        service.sendRequest();
    }

    /**
     * Debuggable description for object.
     * @return string value.
     */
    @Override
    public String toString() {
        return this.getClass().getName() + " { token: " + this.mToken + " }";
    }

}
