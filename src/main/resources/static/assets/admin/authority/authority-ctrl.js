app.controller("authority-ctrl", function ($scope, $http, $location){
    $scope.roles = [];
    $scope.admins = [];
    $scope.authorities = [];

    $scope.initialize = function(){
        //load all roles
        $http.get("/api/roles").then(resp => {
            $scope.roles = resp.data;
        })

        //load staffs and directors(administrators)
        $http.get("/api/accounts?admin=false").then(resp => {
            $scope.admins = resp.data;
        })

        //load authority of staffs and directors
        $http.get("/api/authorities?admin=false").then(resp => {
            $scope.authorities = resp.data;
        }).catch(error => {
            $location.path("/unauthorized");
        })
    }
    $scope.authority_of=function(acc,role){
        if($scope.authorities){
            return $scope.authorities.find(ur => ur.account.username == acc.username && ur.role.id == role.id);
        }
    }

    $scope.authority_changed = function(acc,role){
        var authority = $scope.authority_of(acc,role);
        if(authority){// đã cấp quyền => thu hồi quyền(xóa)
            $scope.revoke_authority(authority);
        }
        else{
            authority ={account: acc,role: role};
            $scope.grant_authority(authority);
        }
    }
//thêm mới authority
    $scope.grant_authority = function(authority){
        $http.post(`/api/authorities`,authority).then(resp => {
            $scope.authorities.push(resp.data);
            alert("Cung cấp quyền sử dụng thành công");
        }).catch(error => {
            alert("Cấp quyền thất bại");
            console.log("Error",error);
        })
    }
//Xóa authority
    $scope.revoke_authority = function(authority){
        console.log(authority.id);
        $http.delete(`/api/authorities/${authority.id}`).then(resp => {
            var index = $scope.authorities.findIndex(a => a.id == authority.id);
            $scope.authorities.splice(index, 1);
            alert("Thu hồi quyền thành công");
        }).catch(error => {
            alert("Thu hồi quyền thất bại");
            console.log("Error",error);
        })
    }

    $scope.initialize();
});