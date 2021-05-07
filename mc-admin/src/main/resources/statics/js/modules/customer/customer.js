$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'customer/customer/list',
        datatype: "json",
        colModel: [
            {
                label: '客户名称',
                name: 'name',
                index: 'name',
                width: 80
            },
            // {
            //     label: '状态',
            //     name: 'status',
            //     index: 'status',
            //     width: 80
            // },

            {
                label: '集群id',
                name: 'clusterId',
                index: 'cluster_id',
                width: 80
            },
            {
                label: 'Domain',
                name: 'domain',
                index: 'domain',
                width: 80
            },
            {
                label: '带宽',
                name: 'bandwidth',
                index: 'bandwidth',
                width: 80
            },
            {
                label: 'DDOS带宽',
                name: 'ddosBandwidth',
                index: 'ddos_bandwidth',
                width: 80
            },
            {
                label: '创建时间',
                name: 'createdTime',
                index: 'created_time',
                width: 80
            },
            {
                label: '更新时间',
                name: 'updatedTime',
                index: 'updated_time',
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
    //集群绑定
    $.ajax({
        type: "POST",
        url: baseURL + "customer/clusters/queryCulster",
        contentType: "application/json",
        success: function (r) {
            if (r.code === 0) {
                for (var index in r.data) {
                    var cur = r.data[index];
                    $("#sel_cluster").append("<option value='" + cur.id + "'>" + cur.name + "</option>");
                }
            }
        }
    });
    $("#sel_cluster").select2({
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
        customer: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.customer = {};
            $("#sel_cluster").val("").trigger("change");
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
                var url = vm.customer.id == null ? "customer/customer/save" : "customer/customer/update";
                vm.customer.clusterIdList = $("#sel_cluster").select2("val");
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.customer),
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
                        url: baseURL + "customer/customer/delete",
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
            $.get(baseURL + "customer/customer/info/" + id, function (r) {
                vm.customer = r.customer;
                $("#sel_cluster").val(vm.customer.clusterIdList).trigger("change");
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