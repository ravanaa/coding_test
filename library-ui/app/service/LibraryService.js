app.service('libraryService',function($http,$log,$q){
    this.getLibraries=function(){
        var def = $q.defer();
        $http.get("/library/api/getAllLibraries/").
        then(function(response){
            def.resolve(response.data);
        },function(errorResponse){
            console.log(errorResponse);
            def.reject("not found")
        })

        return def.promise;
    }
})