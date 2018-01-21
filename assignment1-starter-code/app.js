(function(){
'use strict';

angular.module('LunchCheck',[]).controller('Control',parameter);
parameter.$inject=["$scope"];
function parameter($scope){
	$scope.list="";
	$scope.msg="";

$scope.term=function () { var name=$scope.list;
	if(name==""){$scope.msg= "Please enter data first";}
	else {
			var a=name.split(/,/);
			var i=0;
				while(i<a.length)
					{
						if (a[i].trim()=="")
							{ 
								a.splice(i,1);
								i--;
							};i++;
					};
			if (a.length<=3	){ $scope.msg= "Enjoy!";}
			else $scope.msg="Too much!";
	};  
};
};
})();