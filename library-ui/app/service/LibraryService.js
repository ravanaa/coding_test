

app.service('libraryService',function($http,$log,$q){
    this.getLibraries=function(){
        var def = $q.defer();
        $http.get("/library/api/getAllLibraries/").
        then(function(response){
            console.log(response);
            def.resolve(response.data);
        },function(errorResponse){
            console.log(errorResponse);
            def.reject("not found")
        })

        return def.promise;
    }
    this.saveLibrary=function(library){
       let url = 'library/api/addLibrary'; 
       var req = {libraryName:'Hyderabad Library',descripiton:'city library'};
       $http.post(url,JSON.stringify(req)).then((success)=>{
           console.log("add library success...");
       },(error)=>{
           console.log("add library failed");
       })
    }

    this.getBooks=function(libraryId){
        let url = 'library/api/getBooks/'+libraryId;
        var def = $q.defer();
        $http.get(url).then((success)=>{
            def.resolve(success.data);
        },(error)=>{
            console.log("error"+error);
            def.reject("not found");
        })

        return def.promise;
    }

    this.saveBook = function(book,libraryId){
        let url = 'library/api/addBook/'+libraryId;
        $http.post(url,JSON.stringify(book)).then((success)=>{

        },(error)=>{

        });
    }
})