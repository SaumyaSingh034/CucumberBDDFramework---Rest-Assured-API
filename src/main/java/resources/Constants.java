package resources;

public enum Constants {

    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("/maps/api/place/delete/json");

    private String basePath;

    Constants(String basePath) {
        this.basePath = basePath;
    }

    public String getbasePath(){
        return basePath;
    }
}
