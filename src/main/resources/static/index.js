
angular.module('app', ['ngRoute', 'ngStorage', 'ngCookies']).controller('indexController', function ($scope, $http, $localStorage, $location) {
    const contextPath = 'http://localhost:8189/titanic';


    $scope.loadPage = function (page) {
        $http({
            url: contextPath + '/api/v1/passengers',
            method: 'GET',
            params: {
                p: page,
                size: $scope.size ? $scope.size : null,
                is_male: $scope.filter ? $scope.filter.is_male : null,
                survived: $scope.filter ? $scope.filter.survived : null,
                is_adult: $scope.filter ? $scope.filter.is_adult : null,
                no_relatives: $scope.filter ? $scope.filter.no_relatives : null,
                sort: $scope.sort ?  $scope.sort : null,
                stype: $scope.stype ? $scope.stype : null
            }
        }).then(function (response) {
            $scope.passengersPage = response.data.passengerDtoPage;
            $scope.pages = response.data.passengerDtoPage.totalPages;
            $scope.sumSurvived = response.data.sumSurvivalPassengers;
            $scope.sumFair = response.data.sumFair;
            $scope.sumPassengersWithRelativesOnBoard = response.data.sumPassengersWithRelativesOnBoard;
            console.log($scope.passengersPage);
            let minPageIndex = page - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }

            let maxPageIndex = page + 2;
            if (maxPageIndex > $scope.passengersPage.totalPages) {
                maxPageIndex = $scope.passengersPage.totalPages;
            }

            $scope.paginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);
        });
    };

  $scope.findPassengerByName = function (name) {
        $http({
            url: contextPath + '/api/v1/passengers/' + name,
            method: 'GET'
        }).then(function (response) {
            $scope.passengers = response.data
        });
    };

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.loadPage(1);
});