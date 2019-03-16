package ihr.api.model.User

import com.google.gson.annotations.SerializedName



class FavoriteObject {



    @SerializedName("Rotas")
    private var routes : MutableList<String> = mutableListOf()

    fun getRoute(): MutableList<String> {
        return routes
    }

    fun setRoute(routes: MutableList<String>) {
        this.routes = routes
    }

    fun addRoute(newRoute: String): Boolean {
        return try {

            routes?.add(newRoute)

            true

        } catch (e: Exception) {

            false
        }
    }

    fun removeRoute(index: Int): Boolean {

        return try {

            routes?.removeAt(index)

            true

        } catch (e: Exception) {

            false
        }
    }

    // ------------------------------

    @SerializedName("Guias")
    private var guides : MutableList<String> = mutableListOf()

    fun getGuides(): MutableList<String> {
        return guides
    }

    fun setGuides(guides: MutableList<String>) {
        this.guides = guides
    }

    fun addGuide(newGuide: String): Boolean {
        return try {

            guides?.add(newGuide)

            true

        } catch (e: Exception) {

            false
        }
    }

    fun removeGuide(index: Int): Boolean {

        return try {

            routes?.removeAt(index)

            true

        } catch (e: Exception) {

            false
        }
    }

    // ------------------------------

    @SerializedName("Cidades")
    private var cities : MutableList<String> = mutableListOf()

    fun getCities(): MutableList<String> {
        return cities
    }

    fun setCities(cities: MutableList<String>) {
        this.cities = cities
    }

    fun addCity(newCity: String): Boolean {
        return try {

            cities?.add(newCity)

            true

        } catch (e: Exception) {

            false
        }
    }

    fun removeCity(index: Int): Boolean {

        return try {

            cities?.removeAt(index)

            true

        } catch (e: Exception) {

            false
        }
    }

}