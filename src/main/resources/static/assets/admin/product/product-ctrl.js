app.controller("product-ctrl",function($scope,$http){
    $scope.items = [];
    $scope.cates = [];
    $scope.form = {};
    $scope.name = {};
    $scope.initialize = function(){
        //load product
        $http.get("/api/product").then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.createDate = new Date(item.createDate)
            })
        });
        //load categories
        $http.get("/api/category").then(resp => {
            $scope.cates = resp.data;
        });
    }

    $scope.seach = function(){
        var n = angular.copy($scope.name);
        $http.get('/api/product/seach/' + n ).then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.createDate = new Date(item.createDate)
            })
        });
    }
    //khoi dau
    $scope.initialize();

    //xoa form
    $scope.reset= function(){
        $scope.form = {
            createDate:new Date(),
            image:'cloud-upload.jpg',
            available:true,
        };
    }
    //hien thi len form
    $scope.edit = function(item){
        $scope.form = angular.copy(item);
        $(".nav-tabs a:eq(1)").tab('show');
    }
    //them sp moi
    $scope.create = function(){
        var item = angular.copy($scope.form);
        $http.post(`/api/product`,item).then(resp => {
            resp.data.createDate = new Date(resp.data.createDate)
            $scope.items.push(resp.data);
            $scope.reset();
            alert("Thêm mới thành công!");
        }).catch(error => {
            alert("Lỗi thêm mới sản phẩm!");
            console.log("Error",error);
        });
    }
    //cap nhat sp
    $scope.update = function(){
        var item = angular.copy($scope.form);
        $http.put(`/api/product/${item.id}`,item).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items[index] = item;
            $scope.reset();
            alert("Cập nhật sản phẩm thành công!");
        }).catch(error => {
            alert("Lỗi cập nhật sản phẩm!");
            console.log("Error",error);
        });
    }
    //xoa sp
    $scope.delete = function(item){
        $http.delete(`/api/product/delete/${item.id}`).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items.splice(index,1);
            $scope.reset();
            alert("Xóa sản phẩm thành công!");
        }).catch(error => {
            alert("Lỗi xóa sản phẩm!");
            console.log("Error",error);
        });
    }
    //upload hinh
    $scope.imageChanged = function(files){
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/api/upload/images', data,{
            transformRequest: angular.identity,
            headers: {'Content-Type':undefined}
        }).then(resp => {
            $scope.form.image = resp.data.name;
        }).catch(error => {
            alert("Upload Image False!");
            console.log("Error",error);
        })
    }
    //phan trang
    $scope.pager = {
        page: 0,
        size: 5,
        get items(){
            var start = this.page * this.size;
            return $scope.items.slice(start,start + this.size);
        },
        get count(){
            return Math.ceil(1.0 *$scope.items.length / this.size)
        },
        first(){
            this.page = 0;
        },
        prev(){
            this.page--;
            if(this.page<0){
                this.last();
            }
        },
        next(){
            this.page++;
            if(this.page>=this.count){
                this.first();
            }
        },
        last(){
            this.page = this.count - 1;
        }
    }

});