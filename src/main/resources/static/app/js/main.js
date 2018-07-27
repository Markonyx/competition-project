var festivalApp = angular.module("festivalApp", ['ngRoute']);

festivalApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/',{
        templateUrl: '/app/html/partial/takmicenja.html'
    })
    .when('/festivali/edit/:id',{
        templateUrl: '/app/html/partial/edit-festival.html'
    })
    .when('/takmicenja/edit/:id',{
        templateUrl: '/app/html/partial/edit-takmicenja.html'
    })
    .when('/festivali',{
        templateUrl: '/app/html/partial/festivali.html'
    })
    .when('/takmicenja',{
        templateUrl: '/app/html/partial/takmicenja.html'
    })
    .otherwise({
        redirectTo: '/'
    });
}]);

festivalApp.controller("festivaliCtrl", function($scope, $http, $location){

    var baseUrlFestivali = "/api/festivali";
    var baseUrlMesta = "/api/mesta";

    $scope.pageNum = 0;
    $scope.totalPages = 0;

    $scope.mesta = [];
    $scope.festivali = [];

    $scope.noviFestival = {};
    $scope.noviFestival.naziv = "";
    $scope.noviFestival.organizator = "";
    $scope.noviFestival.datumPocetka = "";
    $scope.noviFestival.cenaKarte = "";
    $scope.noviFestival.kolicina = "";
    $scope.noviFestival.mestoId = "";


    $scope.trazeniFestival = {};
    $scope.trazeniFestival.naziv = "";
    $scope.trazeniFestival.maxCena = "";
    $scope.trazeniFestival.idMesta = "";

    var getFestivali = function(){

        var config = {params: {}};

        config.params.pageNum = $scope.pageNum;

        if($scope.trazeniFestival.naziv != ""){
            config.params.naziv = $scope.trazeniFestival.naziv;
        }

        if($scope.trazeniFestival.idMesta != ""){
            config.params.idMesta = $scope.trazeniFestival.idMesta;
        }

        if($scope.trazeniFestival.maxCena != ""){
            config.params.maxCena = $scope.trazeniFestival.maxCena;
        }


        $http.get(baseUrlFestivali, config)
            .then(
            	function success(res){
            		$scope.festivali = res.data;
            		$scope.totalPages = res.headers('totalPages');
            	},
            	function error(res){
            		alert("Neuspešno dobavljanje festivala!");
            	}
            );
    };

    var getMesta = function(){

        $http.get(baseUrlMesta)
            .then(
            	function success(res){
            		$scope.mesta = res.data;
            	},
            	function error(){
            		alert("Neuspešno dobavljanje mesta!");
            	}
            );

    };

    getFestivali();
    getMesta();

    $scope.nazad = function(){
        if($scope.pageNum > 0) {
            $scope.pageNum = $scope.pageNum - 1;
            getFestivali();
        }
    };

    $scope.napred = function(){
        if($scope.pageNum < $scope.totalPages - 1){
            $scope.pageNum = $scope.pageNum + 1;
            getFestivali();
        }
    };

    $scope.dodaj = function(){
        $http.post(baseUrlFestivali, $scope.noviFestival)
            .then(
            	function success(res){
	                alert("Uspešno dodat festival u bazu.");
	                getFestivali();
	
	                $scope.noviFestival.naziv = "";
	                $scope.noviFestival.organizator = "";
	                $scope.noviFestival.datumPocetka = "";
	                $scope.noviFestival.cenaKarte = "";
	                $scope.noviFestival.kolicina = "";
	                $scope.noviFestival.mestoId = "";
            	},
            	function error(res){
            		alert("Neuspešno dodavanje festivala!");
            	}
            );
    };

    $scope.trazi = function () {
        $scope.pageNum = 0;
        getFestivali();
    }

    $scope.izmeni = function(id){
        $location.path('/festivali/edit/' + id);
    }

    $scope.obrisi = function(id){
        $http.delete(baseUrlFestivali + "/" + id).then(
            function success(data){
                getFestivali();
            },
            function error(data){
                alert("Neuspešno brisanje!");
                console.log(data);
            }
        );

    }
    
    $scope.kupi = function(id){
    	$http.post(baseUrlFestivali + "/" + id)
        .then(
        	function success(res){
                alert("Uspešna kupovina.");
                getFestivali();
        	},
        	function error(res){
        		alert("Neuspešna kupovina!");
        	}
        );
    	
    }
});

festivalApp.controller("takmicenjaCtrl", function($scope, $http, $location){

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
    
   
});

festivalApp.controller("editUcesnikCtrl", function($scope, $http, $routeParams, $location){

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

festivalApp.controller("editFestivalCtrl", function($scope, $http, $routeParams, $location){

    var baseUrlFestivali = "/api/festivali";

    $scope.stariFestival = {};
    $scope.stariFestival.naziv = "";
    $scope.stariFestival.organizator = "";
    $scope.stariFestival.datumPocetka = "";
    $scope.stariFestival.cenaKarte = "";
    $scope.stariFestival.kolicina = "";

    var getStariFestival = function(){

        $http.get(baseUrlFestivali + "/" + $routeParams.id)
            .then(
            	function success(data){
            		$scope.stariFestival = data.data;
            	},
            	function errror(){
            		alert("Neuspešno dobavljanje festivala!")
            	}
            );

    }
    getStariFestival();
    
    $scope.izmeni = function(){
        $http.put(baseUrlFestivali + "/" + $scope.stariFestival.id, $scope.stariFestival)
            .then(
            	function success(){
            		alert("Uspešno izmenjen festival!");
            		$location.path("/");
            	},
            	function error(){
            		alert("Neuspešno izmenjen festival!")
            	}
            );
    }
});












