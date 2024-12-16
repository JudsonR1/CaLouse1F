module Calouse1F {
	requires java.sql;
	requires javafx.controls;
	requires javafx.graphics;
	opens main;
	opens controller;
	opens model;
	opens util;
	opens view;
}