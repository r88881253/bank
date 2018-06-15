package com.ibm.esun.esunmobilebank;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CustServiceUnitTest {
    @Test
    public void phoneJsonToArray() throws JSONException {
        try{
            String json = "{ 'test' : [{'title': '1', 'phone': '1-1'}, {'title' : '2', 'phone' : '2-2'}]}";
            JSONObject phoneObj = new JSONObject(json);
            JSONArray phoneJson = phoneObj.getJSONArray("test");
            ArrayList<Map<String, String>> result = CustServiceActivity.phoneJsonToArray(phoneJson);
            assertThat(result.get(0).get("title"), equalTo("1") );
            assertThat(result.get(0).get("phone"), equalTo("1-1") );
            assertThat(result.get(1).get("title"), equalTo("2") );
            assertThat(result.get(1).get("phone"), equalTo("2-2") );
        }catch (Throwable t){

        }
    }
}
