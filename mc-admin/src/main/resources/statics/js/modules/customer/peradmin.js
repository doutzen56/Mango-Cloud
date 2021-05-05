$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'customer/peradmin/list',
        datatype: "json",
        colModel: [
            // {
            //     label: 'id',
            //     name: 'id',
            //     index: 'id',
            //     width: 50,
            //     key: true
            // },
            {
                label: '用户名',
                name: 'username',
                index: 'username',
                width: 80
            },
            {
                label: '密码',
                name: 'password',
                index: 'password',
                width: 80
            },
            {
                label: '手机',
                name: 'mobile',
                index: 'mobile',
                width: 80
            },
            {
                label: 'Skype',
                name: 'skype',
                index: 'skype',
                width: 80
            },
            {
                label: 'email',
                name: 'email',
                index: 'email',
                width: 80
            },
            {
                label: '语言',
                name: 'language',
                index: 'language',
                width: 80
            },
            {
                label: '创建时间',
                name: 'createdTime',
                index: 'created_time',
                width: 80
            },
            // {
            //     label: '更新时间',
            //     name: 'updatedTime',
            //     index: 'updated_time',
            //     width: 80
            // },
            {
                label: '最近登录',
                name: 'lastLogin',
                index: 'last_login',
                width: 80
            },
            {
                label: '状态', name: 'status', width: 60, formatter: function (value, options, row) {
                    return value === 0 ?
                        '<span class="label label-danger">禁用</span>' :
                        '<span class="label label-success">正常</span>';
                }
            }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        perAdmin: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.perAdmin = {};
        },
        update: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function () {
                var url = vm.perAdmin.id == null ? "customer/peradmin/save" : "customer/peradmin/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.perAdmin),
                    success: function (r) {
                        if (r.code === 0) {
                            layer.msg("操作成功", {icon: 1});
                            vm.reload();
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        } else {
                            layer.alert(r.msg);
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        }
                    }
                });
            });
        },
        del: function (event) {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }
            var lock = false;
            layer.confirm('确定要删除选中的记录？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                if (!lock) {
                    lock = true;
                    $.ajax({
                        type: "POST",
                        url: baseURL + "customer/peradmin/delete",
                        contentType: "application/json",
                        data: JSON.stringify(ids),
                        success: function (r) {
                            if (r.code == 0) {
                                layer.msg("操作成功", {icon: 1});
                                $("#jqGrid").trigger("reloadGrid");
                            } else {
                                layer.alert(r.msg);
                            }
                        }
                    });
                }
            }, function () {
            });
        },
        getInfo: function (id) {
            $.get(baseURL + "customer/peradmin/info/" + id, function (r) {
                vm.perAdmin = r.perAdmin;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                page: page
            }).trigger("reloadGrid");
        }
    }
});