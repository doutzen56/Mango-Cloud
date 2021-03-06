$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'customer/ctrlpluginlimit-req/list',
        datatype: "json",
        colModel: [
                                                {
                        label: 'id',
                        name: 'id',
                        index: 'id',
                        //width: 50,
                        sortable: false,
                        key: true
                    },
                                                                {
                        label: '站点ID',
                        name: 'siteId',
                        index: 'site_id',
                        sortable: false,
                        //width: 80
                    }, 
                                                                {
                        label: '指定的请求速率（以秒为单位），请求速率超过 rate 但没有超过 （rate + brust）的请求会被加上延时。',
                        name: 'rate',
                        index: 'rate',
                        sortable: false,
                        //width: 80
                    }, 
                                                                {
                        label: '请求速率超过 （rate + brust）的请求会被直接拒绝。',
                        name: 'burst',
                        index: 'burst',
                        sortable: false,
                        //width: 80
                    }, 
                                                                {
                        label: '是用来做请求计数的依据，默认的key为：”remote_addr”(客户端IP地址)',
                        name: 'key',
                        index: 'key',
                        sortable: false,
                        //width: 80
                    }, 
                                                                {
                        label: '当请求超过阈值被拒绝时，返回的 HTTP 状态码，默认 503。',
                        name: 'rejectedCode',
                        index: 'rejected_code',
                        sortable: false,
                        //width: 80
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
            ctrlPluginLimit-req: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.ctrlPluginLimit-req = {};
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
                var url = vm.ctrlPluginLimit-req.id == null ? "customer/ctrlpluginlimit-req/save" : "customer/ctrlpluginlimit-req/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.ctrlPluginLimit-req),
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
                        url: baseURL + "customer/ctrlpluginlimit-req/delete",
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
            $.get(baseURL + "customer/ctrlpluginlimit-req/info/" +id, function (r) {
                vm.ctrlPluginLimit-req = r.ctrlPluginLimit-req;
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