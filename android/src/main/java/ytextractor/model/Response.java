package ytextractor.model;


import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("args")
    public Args args;
    @SerializedName("assets")
    public Assets assets;


    public class Args {
        //        public String adaptive_fmts;
        @SerializedName("player_response")
        public String player_response;
//        public String url_encoded_fmt_stream_map;
    }


    public class Assets {
        @SerializedName("js")
        public String js;

        public String getJsData() {
            if (js.startsWith("http") && js.contains("youtube.com")) {
                return js;
            } else return "https://www.youtube.com" + js;
        }


    }
}
	
	
