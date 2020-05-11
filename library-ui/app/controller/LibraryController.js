app.controller('libraryController',function($scope,libraryService){
    // $scope.libraries='[{"libraryId":1,"libraryName":"Central Library","descripiton":"sample library"}],"date":"2020-05-12T01:29:47.638"}';
    libraryService.getLibraries().then(function(data){
        $scope.libraries =data;
    },function(data){
        console.log("function failed");
    })
    console.log($scope.libraries);
});