/*
    The Phoenix Hospitality Management System
    Settings Installer App
    Setting Object Definition Code File
    Copyright (c) 2020 By Troy Marker Enterprises
    All Rights Under Copyright Reserved

    The code in this file was created for use with the Phoenix Hospitality Management System (PHMS).
    Use of this code outside the PHMS is strictly prohibited.
 */
package com.phoenixhosman.installer;

class ObjectSettings {
    private String mCoName;
    private String mCoAddress;
    private String mCoCity;
    private String mCoState;
    private String mCoZip;
    private String mApiUrl;
    private String mLockPass;
    private String mApiKey;

    /**
     * Gets company name.
     * @return the company name
     */

    String getCoName () {
        return mCoName;
    }
    /**
     * Sets company name.
     * @param mCoName the company name
     */
    void setCoName (String mCoName) {
        this.mCoName = mCoName;
    }

    /**
     * Gets company address.
     * @return the company address
     */
    String getCoAddress () {
        return mCoAddress;
    }

    /**
     * Sets company address.
     * @param mCoAddress the company address
     */
    void setCoAddress (String mCoAddress) {
        this.mCoAddress = mCoAddress;
    }

    /**
     * Gets company city.
     * @return the company city
     */
    String getCoCity () {
        return mCoCity;
    }

    /**
     * Sets company city.
     * @param mCoCity the company city
     */
    void setCoCity (String mCoCity) {
        this.mCoCity = mCoCity;
    }

    /**
     * Gets company state.
     * @return the company state
     */
    String getCoState () {
        return mCoState;
    }

    /**
     * Sets company state.
     * @param mCoState the company state
     */
    void setCoState (String mCoState) {
        this.mCoState = mCoState;
    }

    /**
     * Gets company zip.
     * @return the company zip
     */
    String getCoZip () {
        return mCoZip;
    }

    /**
     * Sets company zip.
     * @param mCoZip the company zip
     */
    void setCoZip (String mCoZip) {
        this.mCoZip = mCoZip;
    }

    /**
     * Gets api url.
     * @return the api url
     */
    String getApiUrl () {
        return mApiUrl;
    }

    /**
     * Sets api url.
     * @param mApiUrl the API URL
     */
    void setApiUrl (String mApiUrl) {
        this.mApiUrl = mApiUrl;
    }

    /**
     * Sets Lock Password
     * @param mLockPass The lock Passeword
     */
    void setLockPass (String mLockPass) { this.mLockPass = mLockPass; }

    /**
     * Gets the Lock Password
     * @return the lock password
     */
    String getLockPass () { return mLockPass; }

    /**
     * Gets the Api Key
     * @return the API Key
     */
    String getApiKey() {
        return mApiKey;
    }

    /**
     * Sets the Api Key
     * @param mApiKey the api key
     */
    void setApiKey (String mApiKey) {
        this.mApiKey = mApiKey;
    }
}
