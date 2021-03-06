$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'panel/ctrlsite/list',
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
                        label: '站点名称',
                        name: 'name',
                        index: 'name',
                        sortable: false,
                        //width: 80
                    }, 
                                                                {
                        label: '站点类型',
                        name: 'type',
                        index: 'type',
                        sortable: false,
                        //width: 80
                    }, 
                                                                {
                        label: '是否启用',
                        name: 'status',
                        index: 'status',
                        sortable: false,
                        //width: 80
                    }, 
                                                                {
                        label: '重试次数',
                        name: 'retries',
                        index: 'retries',
                        sortable: false,
                        //width: 80
                    }, 
                                                                {
                        label: '是否开启websocket',
                        name: 'enableWebsocket',
                        index: 'enable_websocket',
                        sortable: false,
                        //width: 80
                    }, 
                                                                {
                        label: '预留字段',
                        name: 'key',
                        index: 'key',
                        sortable: false,
                        //width: 80
                    }, 
                                                                {
                        label: '连接数',
                        name: 'timeoutConnect',
                        index: 'timeout_connect',
                        sortable: false,
                        //width: 80
                    }, 
                                                                {
                        label: '发送数',
                        name: 'timeoutSend',
                        index: 'timeout_send',
                        sortable: false,
                        //width: 80
                    }, 
                                                                {
                        label: '部门ID',
                        name: 'deptId',
                        index: 'dept_id',
                        sortable: false,
                        //width: 80
                    }, 
                                                                {
                        label: '读取数',
                        name: 'timeoutRead',
                        index: 'timeout_read',
                        sortable: false,
                        //width: 80
                    }, 
                                                                {
                        label: '创建人',
                        name: 'createdBy',
                        index: 'created_by',
                        sortable: false,
                        //width: 80
                    }, 
                                                                {
                        label: '创建时间',
                        name: 'createdTime',
                        index: 'created_time',
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
            ctrlSite: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.ctrlSite = {};
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
                var url = vm.ctrlSite.id == null ? "panel/ctrlsite/save" : "panel/ctrlsite/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.ctrlSite),
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
                        url: baseURL + "panel/ctrlsite/delete",
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
            $.get(baseURL + "panel/ctrlsite/info/" +id, function (r) {
                vm.ctrlSite = r.ctrlSite;
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