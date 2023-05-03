package Utils;

public class Constants {

    public static final String BASEURL = ConfigurationReader.get("baseURL");

    public static final String PRODUCTLIST = BASEURL+"/api/productsList";
    public static final String BRANDLIST = BASEURL+"/api/brandsList";
    public static final String SEARCHPRODUCT = BASEURL+"/api/searchProduct";
    public static final String LOGIN = BASEURL+"/api/verifyLogin";
}