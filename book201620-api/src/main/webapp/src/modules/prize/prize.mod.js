/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
(function (ng) {
    var mod = ng.module('prizeModule', ['ngCrud', 'ui.router']);

    mod.constant('prizeModel', {
        name: 'prize',
        displayName: 'Prize',
        url: 'prizes',
        fields: {
            name: {
                displayName: 'Name',
                type: 'String',
                required: true
            },
            datePrize: {
                displayName: 'Date Prize',
                type: 'Date',
                required: true
            },
            author: {
                displayName: 'Author',
                type: 'Reference',
                model: 'authorModel',
                options: [],
                required: true
            }        }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/prize/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('prize', {
                url: '/prizes?page&limit',
                abstract: true,
                
                views: {
                     mainView: {
                        templateUrl: basePath + 'prize.tpl.html',
                        controller: 'prizeCtrl'
                    }
                },
                resolve: {
                    references: ['$q', 'Restangular', function ($q, r) {
                            return $q.all({
                                author: r.all('authors').getList()
                            });
                        }],
                    model: 'prizeModel',
                    prizes: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
            $sp.state('prizeList', {
                url: '/list',
                parent: 'prize',
                views: {
                    prizeView: {
                        templateUrl: basePath + 'list/prize.list.tpl.html',
                        controller: 'prizeListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('prizeNew', {
                url: '/new',
                parent: 'prize',
                views: {
                    prizeView: {
                        templateUrl: basePath + 'new/prize.new.tpl.html',
                        controller: 'prizeNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('prizeInstance', {
                url: '/{prizeId:int}',
                abstract: true,
                parent: 'prize',
                views: {
                    prizeView: {
                        template: '<div ui-view="prizeInstanceView"></div>'
                    }
                },
                resolve: {
                    prize: ['prizes', '$stateParams', function (prizes, $params) {
                            return prizes.get($params.prizeId);
                        }]
                }
            });
            $sp.state('prizeDetail', {
                url: '/details',
                parent: 'prizeInstance',
                views: {
                    prizeInstanceView: {
                        templateUrl: baseInstancePath + 'detail/prize.detail.tpl.html',
                        controller: 'prizeDetailCtrl'
                    }
                }
            });
            $sp.state('prizeEdit', {
                url: '/edit',
                sticky: true,
                parent: 'prizeInstance',
                views: {
                    prizeInstanceView: {
                        templateUrl: baseInstancePath + 'edit/prize.edit.tpl.html',
                        controller: 'prizeEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('prizeDelete', {
                url: '/delete',
                parent: 'prizeInstance',
                views: {
                    prizeInstanceView: {
                        templateUrl: baseInstancePath + 'delete/prize.delete.tpl.html',
                        controller: 'prizeDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
	}]);
})(window.angular);
