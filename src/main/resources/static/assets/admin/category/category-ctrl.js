app.controller("category-ctrl",function($scope,$http){
    $scope.cates = [];
    $scope.form = {};

    $scope.initialize = function () {
        //load category
        $http.get("/api/category").then(resp => {
            $scope.cates = resp.data;
            console.log(resp.data);
        });
    }
    //khoi dau
    $scope.initialize();

    //xoa form
    $scope.reset= function(){
        $scope.form = {
            id:'',
            name:''
        };
    }
    //hien thi len form
    $scope.edit = function(cate){
        $scope.form = angular.copy(cate);
        $(".nav-tabs a:eq(1)").tab('show');
    }
    //them sp moi
    $scope.create = function(){
        var item = angular.copy($scope.form);
        $http.post(`/api/category`,item).then(resp => {
            $scope.cates.push(resp.data);
            $scope.reset();
            alert("Thêm mới thành công!");
        }).catch(error => {
            alert("Lỗi thêm mới loại sản phẩm!");
            console.log("Error",error);
        });
    }
    //cap nhat sp
    $scope.update = function(){
        var item = angular.copy($scope.form);
        $http.put(`/api/category/${item.id}`,item).then(resp => {
            var index = $scope.cates.findIndex(c => c.id == item.id);
            $scope.cates[index] = item;
            $scope.reset();
            alert("Cập nhật loại sản phẩm thành công!");
        }).catch(error => {
            alert("Lỗi cập nhật loại sản phẩm!");
            console.log("Error",error);
        });
    }
    //xoa sp
    $scope.delete = function(cate){
        $http.delete(`/api/category/delete/${cate.id}`).then(resp => {
            var index = $scope.cates.findIndex(c => c.id == cate.id);
            $scope.cates.splice(index,1);
            $scope.reset();
            alert("Xóa loại sản phẩm thành công!");
        }).catch(error => {
            alert("Lỗi xóa loại sản phẩm!");
            console.log("Error",error);
        });
    }


});