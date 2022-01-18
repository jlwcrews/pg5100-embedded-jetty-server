package no.jlwcrews.servlets.api.get

import no.jlwcrews.servlets.api.Query

class GetApiStatus: Query {

    fun getApiStatus(): String {
        return "200"
    }
}


