var competitionApp = angular.module("competitionApp", ['ngRoute']);

competitionApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/',{
        templateUrl: '/app/html/partial/takmicenja.html'
    })
    .when('/takmicenja/edit/:id',{
        templateUrl: '/app/html/partial/edit-takmicenja.html'
    })
    .when('/takmicenja',{
        templateUrl: '/app/html/partial/takmicenja.html'
    })
    .when('/takmicenja/odigraj/', {
			templateUrl : '/app/html/partial/odigrajMec.html'
	})
    .otherwise({
        redirectTo: '/'
    });
}]);

competitionApp.controller("takmicenjaCtrl", function($scope, $http, $location){

    var baseUrlUcesnici = "/api/ucesnici";
    var baseUrlTakmicenja = "/api/takmicenja";

    $scope.pageNum = 0;
    $scope.totalPages = 0;

    $scope.takmicenja = [];
    $scope.ucesnici = [];

    $scope.noviUcesnik = {};
    $scope.noviUcesnik.naziv = "";
    $scope.noviUcesnik.mesto = "";
    $scope.noviUcesnik.email = "";
    $scope.noviUcesnik.takmicenjeId = "";
    $scope.noviUcesnik.odigrano = "0";
    $scope.noviUcesnik.brBodova = "0";
    
    $scope.trazeniUcesnik = {};
    $scope.trazeniUcesnik.naziv = "";
    $scope.trazeniUcesnik.takmicenjeId = "";

    var getUcesnici = function(){

        var config = {params: {}};

        config.params.pageNum = $scope.pageNum;

        if($scope.trazeniUcesnik.naziv != ""){
            config.params.naziv = $scope.trazeniUcesnik.naziv;
        }
        if($scope.trazeniUcesnik.mesto != ""){
            config.params.mesto = $scope.trazeniUcesnik.mesto;
        }

        if($scope.trazeniUcesnik.takmicenjeId != ""){
            config.params.takmicenjeId = $scope.trazeniUcesnik.takmicenjeId;
        }

        $http.get(baseUrlUcesnici, config)
            .then(
            	function success(res){
            		$scope.ucesnici = res.data;
            		$scope.totalPages = res.headers('totalPages');
            	},
            	function error(res){
            		alert("Neuspešno dobavljanje ucesnika!");
            	}
            );
    };

    var getTakmicenja = function(){

        $http.get(baseUrlTakmicenja)
            .then(
            	function success(res){
            		$scope.takmicenja = res.data;
            	},
            	function error(){
            		alert("Neuspešno dobavljanje takmicenja!");
            	}
            );

    };

    getUcesnici();
    getTakmicenja();

    $scope.nazad = function(){
        if($scope.pageNum > 0) {
            $scope.pageNum = $scope.pageNum - 1;
            getUcesnici();
        }
    };

    $scope.napred = function(){
        if($scope.pageNum < $scope.totalPages - 1){
            $scope.pageNum = $scope.pageNum + 1;
            getUcesnici();
        }
    };

    $scope.dodaj = function(){
        $http.post(baseUrlUcesnici, $scope.noviUcesnik)
            .then(
            	function success(res){
	                alert("Uspešno dodat ucesnik u bazu.");
	                getUcesnici();
	
	                $scope.noviUcesnik.naziv = "";
	                $scope.noviUcesnik.mesto = "";
	                $scope.noviUcesnik.email = "";
	                $scope.noviUcesnik.takmicenjeId = "";
            	},
            	function error(res){
            		alert("Neuspešno dodavanje ucesnika!");
            	}
            );
    };

    $scope.trazi = function () {
        $scope.pageNum = 0;
        getUcesnici();
    }

    $scope.izmeni = function(id){
        $location.path('/takmicenja/edit/' + id);
    }

    $scope.obrisi = function(id){
        $http.delete(baseUrlUcesnici + "/" + id).then(
            function success(data){
                getUcesnici();
            },
            function error(data){
                alert("Neuspešno brisanje!");
                console.log(data);
            }
        );

    }
    
    $scope.table = true;
    $scope.prikazi = function() {
    	$scope.table = !$scope.table;
    }
    
    $scope.predji=function(){
    	
      	 $location.path('/takmicenja/odigraj/');
      	 
       }
       
});

competitionApp.controller("odigrajMecCtrl", function($scope, $http, $routeParams, $location){
	
	var baseUrlUcesnici = "/api/ucesnici";
	$scope.ucesnici = [];
	
	$scope.odigraj={};
    $scope.odigrajUcesnici=[];
    
    var getUcesnici = function(){
        $http.get(baseUrlUcesnici)
            .then(
            	function success(res){
            		$scope.ucesnici = res.data;
            	},
            	function error(res){
            		alert("Neuspešno dobavljanje ucesnika!");
            	}
            );
    };
    getUcesnici();
    
    $scope.odigraj=function(u1,u2,ishod){
       	var promise=$http.put(baseUrlUcesnici + "/" + u1 + "/" + u2 + "/" +  ishod);
   		promise.then(
   				function uspeh(res){
   					$scope.ucesnici=res.data;
   					getUcesnici();
   					$location.path('/takmicenja');
   					
   				},
   				function greska(){
   					alert("greska");
   				
   				}
   		);
      }
    
    
});

competitionApp.controller("editUcesnikCtrl", function($scope, $http, $routeParams, $location){

    var baseUrlUcesnici = "/api/ucesnici";

    $scope.stariUcesnik = {};
    $scope.stariUcesnik.naziv = "";
    $scope.stariUcesnik.mesto = "";
    $scope.stariUcesnik.email = "";
    $scope.stariUcesnik.odigrano = "";
    $scope.stariUcesnik.brBodova = "";

    var getStariUcesnik = function(){

        $http.get(baseUrlUcesnici + "/" + $routeParams.id)
            .then(
            	function success(data){
            		$scope.stariUcesnik = data.data;
            	},
            	function error(){
            		alert("Neuspešno dobavljanje ucesnika!")
            	}
            );

    }
    getStariUcesnik();
    
    $scope.izmeni = function(){
        $http.put(baseUrlUcesnici + "/" + $scope.stariUcesnik.id, $scope.stariUcesnik)
            .then(
            	function success(){
            		alert("Uspešno izmenjen ucesnik!");
            		$location.path("/takmicenja");
            	},
            	function error(){
            		alert("Neuspešno izmenjen ucesnik!")
            	}
            );
    }
    
    
    
});














