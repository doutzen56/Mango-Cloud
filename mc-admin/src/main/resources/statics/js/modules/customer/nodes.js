$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'customer/nodes/list',
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
                label: '节点名称',
                name: 'name',
                index: 'name',
                width: 80
            },
            {
                label: '公网IP',
                name: 'publicIp',
                index: 'public_ip',
                width: 80
            },
            {
                label: '内网IP',
                name: 'privateIp',
                index: 'private_ip',
                width: 80
            },
            {
                label: '记录',
                name: 'record',
                index: 'record',
                width: 80
            },
            {
                label: '总带宽(MB)',
                name: 'bandwidthTotal',
                index: 'bandwidth_total',
                width: 80
            },
            {
                label: '带宽临界点(MB)',
                name: 'bandwidthThreshold',
                index: 'bandwidth_threshold',
                width: 80
            },
            {
                label: '当前带宽(MB)',
                name: 'bandwidthCurrent',
                index: 'bandwidth_current',
                width: 80
            },
            {
                label: '连接数',
                name: 'connection',
                index: 'connection',
                width: 80
            },
            {
                label: '创建时间',
                name: 'createdTime',
                index: 'created_time',
                width: 80
            },
            {
                label: '状态', name: 'status', width: 60, formatter: function (value, options, row) {
                    return value === 0 ?
                        '<span class="label label-danger">禁用</span>' :
                        '<span class="label label-success">正常</span>';
                }
            }
            //                                             {
            //     label: '创建人',
            //     name: 'createdBy',
            //     index: 'created_by',
            //     width: 80
            // }
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
    //区域绑定
    $.ajax({
        type: "POST",
        url: baseURL + "customer/nodes/queryArea",
        contentType: "application/json",
        success: function (r) {
            if (r.code === 0) {
                for (var index in r.data) {
                    var cur = r.data[index];
                    $("#sel_area").append("<option value='" + cur.id + "'>" + cur.name + "</option>");
                }
            }
        }
    });
    $("#sel_area").select2({
        minimumResultsForSearch: -1,
        language: {
            noResults: function () {
                return "没有数据...";
            }
        }
    });
    //运营商绑定
    $.ajax({
        type: "POST",
        url: baseURL + "sys/dict/queryDictByType/providerType",
        contentType: "application/json",
        success: function (r) {
            if (r.code === 0) {
                for (var index in r.data) {
                    var cur = r.data[index];
                    $("#sel_provider").append("<option value='" + cur.code + "'>" + cur.value + "</option>");
                }
            }
        }
    });
    $("#sel_provider").select2({
        minimumResultsForSearch: -1,
        language: {
            noResults: function () {
                return "没有数据...";
            }
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        nodes: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.nodes = {status: 1};
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
                var url = vm.nodes.id == null ? "customer/nodes/save" : "customer/nodes/update";
                vm.nodes.areaId=$("#sel_area").select2("val");
                //vm.nodes.areaName=$("#sel_area").select2("data")[0].text;
                vm.nodes.providerType=$("#sel_provider").select2("val");
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.nodes),
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
                        url: baseURL + "customer/nodes/delete",
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
            $.get(baseURL + "customer/nodes/info/" + id, function (r) {
                vm.nodes = r.nodes;
                $("#sel_area").val(vm.nodes.areaId).trigger("change");
                $("#sel_provider").val(vm.nodes.providerType).trigger("change");
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