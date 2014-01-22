// Compiled by ClojureScript 0.0-2138
goog.provide('stefon_compojure_test');
goog.require('cljs.core');
goog.require('ajax.core');
goog.require('ajax.core');
goog.require('stefon_compojure.service');
goog.require('stefon_compojure.service');
goog.require('cemerick.cljs.test');
goog.require('cemerick.cljs.test');
stefon_compojure_test.request_body = new cljs.core.PersistentArrayMap(null, 7, [new cljs.core.Keyword(null,"title","title",1124275658),"Latest In Biotech",new cljs.core.Keyword(null,"content","content",1965434859),"Lorem ipsum",new cljs.core.Keyword(null,"content-type","content-type",1799574400),"txt",new cljs.core.Keyword(null,"created-date","created-date",2970392293),"09/01/2013",new cljs.core.Keyword(null,"modified-date","modified-date",3061274532),"09/01/2013",new cljs.core.Keyword(null,"assets","assets",3900663541),cljs.core.PersistentVector.EMPTY,new cljs.core.Keyword(null,"tags","tags",1017456523),cljs.core.PersistentVector.EMPTY], null);
stefon_compojure_test.service = (function service(){return cemerick.cljs.test.test_function.call(null,stefon_compojure_test.service);
});
stefon_compojure_test.service = cljs.core.vary_meta.call(null,stefon_compojure_test.service,cljs.core.assoc,new cljs.core.Keyword(null,"name","name",1017277949),new cljs.core.Symbol(null,"service","service",343621742,null),new cljs.core.Keyword(null,"test","test",1017460740),(function service_test(){try{var values__5316__auto__ = cljs.core._conj.call(null,cljs.core._conj.call(null,cljs.core.List.EMPTY,"fubar"),stefon_compojure.service.fubar.call(null));var result__5317__auto__ = cljs.core.apply.call(null,cljs.core._EQ_,values__5316__auto__);if(cljs.core.truth_(result__5317__auto__))
{cemerick.cljs.test.do_report.call(null,new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"actual","actual",3885931776),cljs.core.cons.call(null,cljs.core._EQ_,values__5316__auto__),new cljs.core.Keyword(null,"type","type",1017479852),new cljs.core.Keyword(null,"pass","pass",1017337731),new cljs.core.Keyword(null,"message","message",1968829305),null,new cljs.core.Keyword(null,"expected","expected",3373152810),cljs.core.list(new cljs.core.Symbol(null,"=","=",-1640531466,null),cljs.core.list(new cljs.core.Symbol("cs","fubar","cs/fubar",-1542541879,null)),"fubar")], null));
} else
{cemerick.cljs.test.do_report.call(null,new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"actual","actual",3885931776),cljs.core._conj.call(null,cljs.core._conj.call(null,cljs.core.List.EMPTY,cljs.core.cons.call(null,new cljs.core.Symbol(null,"=","=",-1640531466,null),values__5316__auto__)),new cljs.core.Symbol(null,"not","not",-1640422260,null)),new cljs.core.Keyword(null,"type","type",1017479852),new cljs.core.Keyword(null,"fail","fail",1017039504),new cljs.core.Keyword(null,"message","message",1968829305),null,new cljs.core.Keyword(null,"expected","expected",3373152810),cljs.core.list(new cljs.core.Symbol(null,"=","=",-1640531466,null),cljs.core.list(new cljs.core.Symbol("cs","fubar","cs/fubar",-1542541879,null)),"fubar")], null));
}
return result__5317__auto__;
}catch (e5448){if((e5448 instanceof Error))
{var t__5353__auto__ = e5448;return cemerick.cljs.test.do_report.call(null,new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"actual","actual",3885931776),t__5353__auto__,new cljs.core.Keyword(null,"type","type",1017479852),new cljs.core.Keyword(null,"error","error",1110689146),new cljs.core.Keyword(null,"message","message",1968829305),null,new cljs.core.Keyword(null,"expected","expected",3373152810),cljs.core.list(new cljs.core.Symbol(null,"=","=",-1640531466,null),cljs.core.list(new cljs.core.Symbol("cs","fubar","cs/fubar",-1542541879,null)),"fubar")], null));
} else
{if(new cljs.core.Keyword(null,"else","else",1017020587))
{throw e5448;
} else
{return null;
}
}
}}));
cemerick.cljs.test.register_test_BANG_.call(null,new cljs.core.Symbol(null,"stefon-compojure-test","stefon-compojure-test",-1703935514,null),stefon_compojure_test.service);
stefon_compojure_test.create_post = (function create_post(){return cemerick.cljs.test.test_function.call(null,stefon_compojure_test.create_post);
});
stefon_compojure_test.create_post = cljs.core.vary_meta.call(null,stefon_compojure_test.create_post,cljs.core.assoc,new cljs.core.Keyword(null,"name","name",1017277949),new cljs.core.Symbol(null,"create-post","create-post",2114572426,null),new cljs.core.Keyword(null,"test","test",1017460740),(function create_post_test(){return stefon_compojure.service.create_post.call(null,stefon_compojure_test.request_body);
}));
cemerick.cljs.test.register_test_BANG_.call(null,new cljs.core.Symbol(null,"stefon-compojure-test","stefon-compojure-test",-1703935514,null),stefon_compojure_test.create_post);
