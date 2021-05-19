$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'customer/ctrlpluginproxycache/list',
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
                        label: '是否开启缓存',
                        name: 'cacheState',
                        index: 'cache_state',
                        sortable: false,
                        //width: 80
                    }, 
                                                                {
                        label: '缓存区域',
                        name: 'cacheZone',
                        index: 'cache_zone',
                        sortable: false,
                        //width: 80
                    }, 
                                                                {
                        label: '缓存key',
                        name: 'cacheKey',
                        index: 'cache_key',
                        sortable: false,
                        //width: 80
                    }, 
                                                                {
                        label: 'http方法',
                        name: 'cacheMethod',
                        index: 'cache_method',
                        sortable: false,
                        //width: 80
                    }, 
                                                                {
                        label: 'http状态码',
                        name: 'cacheHttpStatus',
                        index: 'cache_http_status',
                        sortable: false,
                        //width: 80
                    }, 
                                                                {
                        label: '浏览器缓存时间',
                        name: 'cacheBrowser',
                        index: 'cache_browser',
                        sortable: false,
                        //width: 80
                    }, 
                                                                {
                        label: '边缘节点缓存时间',
                        name: 'cacheEdge',
                        index: 'cache_edge',
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
            ctrlPluginProxyCache: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.ctrlPluginProxyCache = {};
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
                var url = vm.ctrlPluginProxyCache.id == null ? "customer/ctrlpluginproxycache/save" : "customer/ctrlpluginproxycache/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.ctrlPluginProxyCache),
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
                        url: baseURL + "customer/ctrlpluginproxycache/delete",
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
            $.get(baseURL + "customer/ctrlpluginproxycache/info/" +id, function (r) {
                vm.ctrlPluginProxyCache = r.ctrlPluginProxyCache;
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