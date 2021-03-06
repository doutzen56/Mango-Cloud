$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'customer/clusters/list',
        datatype: "json",
        colModel: [
            {
                label: '集群名称',
                name: 'name',
                index: 'name',
                width: 80
            },
            {
                label: '地理位置',
                name: 'location',
                index: 'location',
                width: 80
            },
            {
                label: '记录',
                name: 'record',
                index: 'record',
                width: 80
            }
            ,
            {
                label: '创建人',
                name: 'createdBy',
                index: 'created_by',
                width: 80
            },
            {
                label: '创建时间',
                name: 'createdTime',
                index: 'created_time',
                width: 80
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
    $.ajax({
        type: "POST",
        url: baseURL + "customer/nodes/queryMap/1",
        contentType: "application/json",
        success: function (r) {
            if (r.code === 0) {
                for (var a in r.data) {
                    $("#sel_nodes").append("<option value='" + a + "'>" + r.data[a] + "</option>");
                }
            }
        }
    });
    $("#sel_nodes").select2({
        placeholder: '',
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
        clusters: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.clusters = {status: 1};
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
                var url = vm.clusters.id == null ? "customer/clusters/save" : "customer/clusters/update";
                //获取选择的数据
                vm.clusters.nodeIdList = $("#sel_nodes").select2("val");
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.clusters),
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
                        url: baseURL + "customer/clusters/delete",
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
            $.get(baseURL + "customer/clusters/info/" + id, function (r) {
                vm.clusters = r.clusters;
                //绑定下拉列表选择项
                $("#sel_nodes").val(vm.clusters.nodeIdList).trigger("change");
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