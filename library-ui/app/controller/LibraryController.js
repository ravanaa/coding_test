app.controller('libraryController',function($scope,libraryService){
   
    $scope.name = "Jai Ganesh";
    $scope.visible=false;
    $scope.selectedLibrary = 0;
    libraryService.getLibraries().then(function(data){
        $scope.libraries =data.data;
    },function(error){
        console.log(eror+"function failed");
    });
  
    $scope.addLibrary=function(){
        $scope.libraries.push({'libraryId':" ",'libraryName':" ",'descripiton':" "});
    }

    $scope.saveLibrary=function(){
        angular.forEach($scope.libraries,(selected) => {
            if(selected.checked){
                console.log(selected);
                var library ='{"libraryName":"'+selected.libraryName+'","descripiton":"'+selected.descripiton+'"}';
                console.log(JSON.parse(library));
                libraryService.saveLibrary(library);
            }
        });
    }
    $scope.showBooks = function(libraryId){
        if(!$scope.visible){
        $scope.visible = true;
        $scope.selectedLibrary = libraryId;
        console.log("selected library : "+libraryId);
         libraryService.getBooks(libraryId).then((response)=>{
             console.log("books "+ response.data);
            $scope.books = response.data;
         },(error)=>{
             console.error(error);
         })
        }else
            $scope.visible = false;
    }

    $scope.addBook = function(){
        $scope.books.push({"isbn":"","bookTitle":"","language":"","publishingYear":"","author":""})
    }
    $scope.saveBook = function(){
        angular.forEach($scope.books,(book)=>{
            if(book.checked){
                var saveBook = {isbn:book.isbn,bookTitle:book.bookTitle,language:book.language,publishingYear:book.publishingYear,author:book.author};
                libraryService.saveBook(saveBook,$scope.selectedLibrary);
            }
        })
    }
    $scope.deleteBook = function(){

    }
});