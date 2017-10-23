function productAdd () {
    $("#addProductForm")[0].reset();
    $("#addProductModal").modal("show");
}

function productEdit () {
    var selectContent = $('#productTable').bootstrapTable('getSelections')[0];
    if(typeof(selectContent) == 'undefined') {
        alert('请选择一列数据!');
        return false;
    }else{
        console.info(selectContent);
        $("#editProductName").val(selectContent.productName);
        $("#editProductDetail").val(selectContent.productDetail);
        $("#editAuthor").val(selectContent.author);
        $("#editPrice").val(selectContent.price);
        $("#editProductId").val(selectContent.productId);
        $("#editSectionId").val(selectContent.sectionId);
        $("#editCreateBy").val(selectContent.createBy);
        $("#editCreateDate").val(selectContent.createDate);
        $("#editProductModal").modal("show");
    }
}

function doDeleteProduct () {
    var selectContent = $('#productTable').bootstrapTable('getSelections')[0];
    if(typeof(selectContent) == 'undefined') {
        alert('请选择一列数据!');
        return false;
    }else{
        var con = confirm("确定要删除选中的数据吗？");
        if(con == true){
            $.ajax({
                url : "/product/doDeleteProduct",
                type : "post",
                dataType : "json",
                data: 'productId='+selectContent.productId+'&sectionId='+sectionId,
                success : function(result) {
                    if(result.code == 0){
                        alert("删除成功");
                        $('#productTable').bootstrapTable('refresh');
                    }else{
                        alert("删除失败");
                    }
                }
            });
        }

    }
}

function doAddProduct(){
    //var validator = $("#addProductForm").validate({
     //   submitHandler: function(form) {  //通过之后回调
           // showTips("处理中.....",5);
            var param = $("#addProductForm").serialize();
            param += '&sectionId=' + sectionId;
            $.ajax({
                url : "/product/doAddProduct",
                type : "post",
                dataType : "json",
                data: param,
                success : function(result) {
                    if(result.code == 0){
                        alert("新增成功");
                        $('#addProductModal').modal('hide');
                        $('#productTable').bootstrapTable('refresh');
                    }else{
                        alert("新增失败");
                    }
                }
            });
     //   }
   // });

}

function doEditProduct(){
    //var validator = $("#addProductForm").validate({
    //   submitHandler: function(form) {  //通过之后回调
    // showTips("处理中.....",5);
    var param = $("#editProductForm").serialize();
    param += '&sectionId=' + sectionId;
    $.ajax({
        url : "/product/doUpdateProduct",
        type : "post",
        dataType : "json",
        data: param,
        success : function(result) {
            if(result.code == 0){
                alert("修改成功");
                $('#editProductModal').modal('hide');
                $('#productTable').bootstrapTable('refresh');
            }else{
                alert("修改失败");
            }
        }
    });
    //   }
    // });

}

$().ready(function() {

    // bootstrapTable 表格数据初始化
    $('#productTable').bootstrapTable({
        url:'/product/doListProduct?sectionId='+sectionId,
        type: 'post',
        dataType:"json",
        pagination: true,
        singleSelect:true,
        pageNumber:1,
        pageSize:10,
        search:false,
        sortable: false,
        sidePagination: 'client',

        'columns':[
        {
            checkbox: true
        }, {
            title: '产品id',
            field: 'productId',
            visible: false
        }, {
            title: '栏目id',
            field: 'sectionId',
            visible: false
        }, {
            title: '产品名称',
            field: 'productName'
        }, {
            title: '产品详情',
            field: 'productDetail',
            visible: false
        }, {
            title: '作者',
            field: 'author'
        }, {
            title: '价格',
            field: 'price'
        }, {
            title: '报名人数',
            field: 'number'
        }, {
            title: '出发日期',
            field: 'departureDate'
        }, {
            title: '创建者',
            field: 'createBy',
            visible: false
        }, {
            title: '创建日期',
            field: 'createDate',
            visible: false
        }
        ],
        'onLoadSuccess': function(data) {

           // $('.pagination-detail')[0].style.display = 'none';

        }
    });


});