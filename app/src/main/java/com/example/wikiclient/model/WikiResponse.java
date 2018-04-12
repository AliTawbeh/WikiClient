package com.example.wikiclient.model;

import com.google.gson.JsonObject;

/**
 * Created by Ali on 07-Apr-18.
 */

public class WikiResponse {

    public Parse parse;
    public WikiError error;

    public class Parse{
        public String title;
        public int pageId;
        public JsonObject text;
    }
    public class WikiError{
        public String code;
        public String info;
    }
}
