//
// OpenPage
// - Abre página no frame (chamando da janela)
//

function OpenPage(file)
{
	self.frames['Frame'].location.href = file;
}

//
// InOpenPage
// - Abre página no frame (chamando do mesmo frame)
//

function InOpenPage(file)
{
	parent.frames['Frame'].location.href = file;
}

var qm_si, qm_li, qm_lo, qm_tt, qm_th, qm_ts, qm_la;
	var qp = "parentNode";
	var qc = "className";
	var qm_t = navigator.userAgent;
	var qm_o = qm_t.indexOf("Opera") + 1;
	var qm_s = qm_t.indexOf("afari") + 1;
	var qm_s2 = qm_s && window.XMLHttpRequest;
	var qm_n = qm_t.indexOf("Netscape") + 1;
	var qm_v = parseFloat(navigator.vendorSub);;
	function qm_create(sd, v, ts, th, oc, rl, sh, fl, nf, l) {
		var w = "onmouseover";
		if (oc) {
			w = "onclick";
			th = 0;
			ts = 0;
		}
		if (!l) {
			l = 1;
			qm_th = th;
			sd = document.getElementById("qm" + sd);
			if (window.qm_pure)
				sd = qm_pure(sd);
			sd[w] = function(e) {
				qm_kille(e)
			};
			document[w] = qm_bo;
			sd.style.zoom = 1;
			if (sh)
				x2("qmsh", sd, 1);
			if (!v)
				sd.ch = 1;
		} else if (sh)
			sd.ch = 1;
		if (sh)
			sd.sh = 1;
		if (fl)
			sd.fl = 1;
		if (rl)
			sd.rl = 1;
		sd.style.zIndex = l + "" + 1;
		var lsp;
		var sp = sd.childNodes;
		for ( var i = 0; i < sp.length; i++) {
			var b = sp[i];
			if (b.tagName == "A") {
				lsp = b;
				b[w] = qm_oo;
				b.qmts = ts;
				if (l == 1 && v) {
					b.style.styleFloat = "none";
					b.style.cssFloat = "none";
				}
			}
			if (b.tagName == "DIV") {
				if (window.showHelp && !window.XMLHttpRequest)
					sp[i].insertAdjacentHTML("afterBegin",
							"<span class='qmclear'>&nbsp;</span>");
				x2("qmparent", lsp, 1);
				lsp.cdiv = b;
				b.idiv = lsp;
				if (qm_n && qm_v < 8 && !b.style.width)
					b.style.width = b.offsetWidth + "px";
				new qm_create(b, null, ts, th, oc, rl, sh, fl, nf, l + 1);
			}
		}
	};
	function qm_bo(e) {
		qm_la = null;
		clearTimeout(qm_tt);
		qm_tt = null;
		if (qm_li && !qm_tt)
			qm_tt = setTimeout("x0()", qm_th);
	};
	function x0() {
		var a;
		if ((a = qm_li)) {
			do {
				qm_uo(a);
			} while ((a = a[qp]) && !qm_a(a))
		}
		qm_li = null;
	};
	function qm_a(a) {
		if (a[qc].indexOf("qmmc") + 1)
			return 1;
	};
	function qm_uo(a, go) {
		if (!go && a.qmtree)
			return;
		if (window.qmad && qmad.bhide)
			eval(qmad.bhide);
		a.style.visibility = "";
		x2("qmactive", a.idiv);
	};;
	function qa(a, b) {
		return String.fromCharCode(a.charCodeAt(0)
				- (b - (parseInt(b / 2) * 2)));
	}
	function qm_oo(e, o, nt) {
		if (!o)
			o = this;
		if (qm_la == o)
			return;
		if (window.qmad && qmad.bhover && !nt)
			eval(qmad.bhover);
		if (window.qmwait) {
			qm_kille(e);
			return;
		}
		clearTimeout(qm_tt);
		qm_tt = null;
		if (!nt && o.qmts) {
			qm_si = o;
			qm_tt = setTimeout("qm_oo(new Object(),qm_si,1)", o.qmts);
			return;
		}
		var a = o;
		if (a[qp].isrun) {
			qm_kille(e);
			return;
		}
		qm_la = o;
		var go = true;
		while ((a = a[qp]) && !qm_a(a)) {
			if (a == qm_li)
				go = false;
		}
		if (qm_li && go) {
			a = o;
			if ((!a.cdiv) || (a.cdiv && a.cdiv != qm_li))
				qm_uo(qm_li);
			a = qm_li;
			while ((a = a[qp]) && !qm_a(a)) {
				if (a != o[qp])
					qm_uo(a);
				else
					break;
			}
		}
		var b = o;
		var c = o.cdiv;
		if (b.cdiv) {
			var aw = b.offsetWidth;
			var ah = b.offsetHeight;
			var ax = b.offsetLeft;
			var ay = b.offsetTop;
			if (c[qp].ch) {
				aw = 0;
				if (c.fl)
					ax = 0;
			} else {
				if (c.rl) {
					ax = ax - c.offsetWidth;
					aw = 0;
				}
				ah = 0;
			}
			if (qm_o) {
				ax -= b[qp].clientLeft;
				ay -= b[qp].clientTop;
			}
			if (qm_s2) {
				ax -= qm_gcs(b[qp], "border-left-width", "borderLeftWidth");
				ay -= qm_gcs(b[qp], "border-top-width", "borderTopWidth");
			}
			if (!c.ismove) {
				c.style.left = (ax + aw) + "px";
				c.style.top = (ay + ah) + "px";
			}
			x2("qmactive", o, 1);
			if (window.qmad && qmad.bvis)
				eval(qmad.bvis);
			c.style.visibility = "inherit";
			qm_li = c;
		} else if (!qm_a(b[qp]))
			qm_li = b[qp];
		else
			qm_li = null;
		qm_kille(e);
	};
	function qm_gcs(obj, sname, jname) {
		var v;
		if (document.defaultView && document.defaultView.getComputedStyle)
			v = document.defaultView.getComputedStyle(obj, null)
					.getPropertyValue(sname);
		else if (obj.currentStyle)
			v = obj.currentStyle[jname];
		if (v && !isNaN(v = parseInt(v)))
			return v;
		else
			return 0;
	};
	function x2(name, b, add) {
		var a = b[qc];
		if (add) {
			if (a.indexOf(name) == -1)
				b[qc] += (a ? ' ' : '') + name;
		} else {
			b[qc] = a.replace(" " + name, "");
			b[qc] = b[qc].replace(name, "");
		}
	};
	function qm_kille(e) {
		if (!e)
			e = event;
		e.cancelBubble = true;
		if (e.stopPropagation && !(qm_s && e.type == "click"))
			e.stopPropagation();
	};
	function qm_pure(sd) {
		if (sd.tagName == "UL") {
			var nd = document.createElement("DIV");
			nd.qmpure = 1;
			var c;
			if (c = sd.style.cssText)
				nd.style.cssText = c;
			qm_convert(sd, nd);
			var csp = document.createElement("SPAN");
			csp.className = "qmclear";
			csp.innerHTML = "&nbsp;";
			nd.appendChild(csp);
			sd = sd[qp].replaceChild(nd, sd);
			sd = nd;
		}
		return sd;
	};
	function qm_convert(a, bm, l) {
		if (!l) {
			bm.className = a.className;
			bm.id = a.id;
		}
		var ch = a.childNodes;
		for ( var i = 0; i < ch.length; i++) {
			if (ch[i].tagName == "LI") {
				var sh = ch[i].childNodes;
				for ( var j = 0; j < sh.length; j++) {
					if (sh[j]
							&& (sh[j].tagName == "A" || sh[j].tagName == "SPAN"))
						bm.appendChild(ch[i].removeChild(sh[j]));
					if (sh[j] && sh[j].tagName == "UL") {
						var na = document.createElement("DIV");
						var c;
						if (c = sh[j].style.cssText)
							na.style.cssText = c;
						if (c = sh[j].className)
							na.className = c;
						na = bm.appendChild(na);
						new qm_convert(sh[j], na, 1)
					}
				}
			}
		}
	}/*     */
	
	var qmad = new Object();
	qmad.bvis = "";
	qmad.bhide = "";
	qmad.bhover = "";
	
	/*******  Menu 0 Add-On Settings *******/
	var a = qmad.qm0 = new Object();

	// Rounded Corners Add On
	a.rcorner_size = 6;
	a.rcorner_container_padding = 0;
	a.rcorner_border_color = "#dadada";
	a.rcorner_bg_color = "#F7F7F7";
	a.rcorner_apply_corners = new Array(false, true, true, true);
	a.rcorner_top_line_auto_inset = true;

	// Rounded Items Add On
	a.ritem_size = 4;
	a.ritem_apply = "main";
	a.ritem_main_apply_corners = new Array(true, true, false, false);
	a.ritem_show_on_actives = true;
	
	/*           */qmad.rcorner = new Object();
	if (qmad.bvis.indexOf("qm_rcorner(b.cdiv);") == -1)
		qmad.bvis += "qm_rcorner(b.cdiv);";
	if (qmad.bhide.indexOf("qm_rcorner(a,1);") == -1)
		qmad.bhide += "qm_rcorner(a,1);";;
	function qm_rcorner(a, hide, force) {
		var z;
		if (!hide
				&& ((z = window.qmv) && (z = z.addons) && (z = z.round_corners) && !z["on"
						+ qm_index(a)]))
			return;
		var q = qmad.rcorner;
		if ((!hide && !a.hasrcorner) || force) {
			var ss;
			if (!a.settingsid) {
				var v = a;
				while ((v = v.parentNode)) {
					if (v.className.indexOf("qmmc") + 1) {
						a.settingsid = v.id;
						break;
					}
				}
			}
			ss = qmad[a.settingsid];
			if (!ss)
				return;
			if (!ss.rcorner_size)
				return;
			q.size = ss.rcorner_size;
			q.offset = ss.rcorner_container_padding;
			if (!q.offset)
				q.offset = 5;
			q.background = ss.rcorner_bg_color;
			if (!q.background)
				q.background = "transparent";
			q.border = ss.rcorner_border_color;
			if (!q.border)
				q.border = "#ff0000";
			q.angle = ss.rcorner_angle_corners;
			q.corners = ss.rcorner_apply_corners;
			if (!q.corners || q.corners.length < 4)
				q.corners = new Array(true, 1, 1, 1);
			q.tinset = 0;
			if (ss.rcorner_top_line_auto_inset && qm_a(a[qp]))
				q.tinset = a.idiv.offsetWidth;
			q.opacity = ss.rcorner_opacity;
			if (q.opacity && q.opacity != 1) {
				var addf = "";
				if (window.showHelp)
					addf = "filter:alpha(opacity=" + (q.opacity * 100) + ");";
				q.opacity = "opacity:" + q.opacity + ";" + addf;
			} else
				q.opacity = "";
			var f = document.createElement("SPAN");
			x2("qmrcorner", f, 1);
			var fs = f.style;
			fs.position = "absolute";
			fs.display = "block";
			fs.visibility = "inherit";
			var size = q.size;
			q.mid = parseInt(size / 2);
			q.ps = new Array(size + 1);
			var t2 = 0;
			q.osize = q.size;
			if (!q.angle) {
				for ( var i = 0; i <= size; i++) {
					if (i == q.mid)
						t2 = 0;
					q.ps[i] = t2;
					t2 += Math.abs(q.mid - i) + 1;
				}
				q.osize = 1;
			}
			var fi = "";
			for ( var i = 0; i < size; i++)
				fi += qm_rcorner_get_span(size, i, 1, q.tinset);
			fi += '<span qmrcmid=1 style="background-color:'+q.background+';border-color:'+q.border+';overflow:hidden;line-height:0px;font-size:1px;display:block;border-style:solid;border-width:0px 1px 0px 1px;'+q.opacity+'"></span>';
			for ( var i = size - 1; i >= 0; i--)
				fi += qm_rcorner_get_span(size, i);
			f.innerHTML = fi;
			f = a.parentNode.appendChild(f);
			a.hasrcorner = f;
		}
		var c = q.offset;
		var b = a.hasrcorner;
		if (b) {
			if (hide)
				b.style.visibility = "hidden";
			else {
				if (!a.offsetWidth)
					a.style.visibility = "inherit";
				a.style.top = (parseInt(a.style.top) + c) + "px";
				a.style.left = (parseInt(a.style.left) + c) + "px";
				b.style.width = (a.offsetWidth + (c * 2)) + "px";
				b.style.height = (a.offsetHeight + (c * 2)) + "px";
				var ft = 0;
				var fl = 0;
				if (qm_o) {
					ft = b[qp].clientTop;
					fl = b[qp].clientLeft;
				}
				if (qm_s2) {
					ft = qm_gcs(b[qp], "border-top-width", "borderTopWidth");
					fl = qm_gcs(b[qp], "border-left-width", "borderLeftWidth");
				}
				b.style.top = (a.offsetTop - c - ft) + "px";
				b.style.left = (a.offsetLeft - c - fl) + "px";
				b.style.visibility = "inherit";
				var s = b.childNodes;
				for ( var i = 0; i < s.length; i++) {
					if (s[i].getAttribute("qmrcmid"))
						s[i].style.height = Math.abs((a.offsetHeight
								- (q.osize * 2) + (c * 2)))
								+ "px";
				}
			}
		}
	};
	function qm_rcorner_get_span(size, i, top, tinset) {
		var q = qmad.rcorner;
		var mlmr;
		if (i == 0) {
			var mo = q.ps[size] + q.mid;
			if (q.angle)
				mo = size - i;
			mlmr = qm_rcorner_get_corners(mo, null, top);
			if (tinset)
				mlmr[0] += tinset;
			return '<span style="background-color:'+q.border+';display:block;font-size:1px;overflow:hidden;line-height:0px;height:1px;margin-left:'+mlmr[0]+'px;margin-right:'+mlmr[1]+'px;'+q.opacity+'"></span>';
		} else {
			var md = size - (i);
			var ih = 1;
			var bs = 1;
			if (!q.angle) {
				if (i >= q.mid)
					ih = Math.abs(q.mid - i) + 1;
				else {
					bs = Math.abs(q.mid - i) + 1;
					md = q.ps[size - i] + q.mid;
				}
				if (top)
					q.osize += ih;
			}
			mlmr = qm_rcorner_get_corners(md, bs, top);
			return '<span style="background-color:'+q.background+';border-color:'+q.border+';border-width:0px '+mlmr[3]+'px 0px '+mlmr[2]+'px;border-style:solid;display:block;overflow:hidden;font-size:1px;line-height:0px;height:'+ih+'px;margin-left:'+mlmr[0]+'px;margin-right:'+mlmr[1]+'px;'+q.opacity+'"></span>';
		}
	};
	function qm_rcorner_get_corners(mval, bval, top) {
		var q = qmad.rcorner;
		var ml = mval;
		var mr = mval;
		var bl = bval;
		var br = bval;
		if (top) {
			if (!q.corners[0]) {
				ml = 0;
				bl = 1;
			}
			if (!q.corners[1]) {
				mr = 0;
				br = 1;
			}
		} else {
			if (!q.corners[2]) {
				mr = 0;
				br = 1;
			}
			if (!q.corners[3]) {
				ml = 0;
				bl = 1;
			}
		}
		return new Array(ml, mr, bl, br);
	}/*     */
	
	qmad.br_navigator = navigator.userAgent.indexOf("Netscape") + 1;
	qmad.br_version = parseFloat(navigator.vendorSub);
	qmad.br_oldnav6 = qmad.br_navigator && qmad.br_version < 7;
	qmad.br_strict = (dcm = document.compatMode) && dcm == "CSS1Compat";
	qmad.br_ie = window.showHelp;
	qmad.str = (qmad.br_ie && !qmad.br_strict);
	if (!qmad.br_oldnav6) {
		if (!qmad.ritem) {
			qmad.ritem = new Object();
			if (qmad.bvis.indexOf("qm_ritem_a(b.cdiv);") == -1) {
				qmad.bvis += "qm_ritem_a(b.cdiv);";
				qmad.bhide += "qm_ritem_a_hide(a);";
			}
			if (window.attachEvent)
				window.attachEvent("onload", qm_ritem_init);
			else if (window.addEventListener)
				window.addEventListener("load", qm_ritem_init, 1);
			var ca = "cursor:pointer;";
			if (qmad.br_ie)
				ca = "cursor:hand;";
			var wt = '<style type="text/css">.qmvritemmenu{}';
			wt += ".qmmc .qmritem span{" + ca + "}";
			document.write(wt + '</style>');
		}
	};
	function qm_ritem_init(e, spec) {
		var z;
		if ((z = window.qmv)
				&& (z = z.addons)
				&& (z = z.ritem)
				&& (!z["on" + qmv.id] && z["on" + qmv.id] != undefined && z["on"
						+ qmv.id] != null))
			return;
		qm_ts = 1;
		var q = qmad.ritem;
		var a, b, r, sx, sy;
		z = window.qmv;
		for (i = 0; i < 10; i++) {
			if (!(a = document.getElementById("qm" + i))
					|| (!isNaN(spec) && spec != i))
				continue;
			var ss = qmad[a.id];
			if (ss && ss.ritem_size) {
				q.size = ss.ritem_size;
				q.apply = ss.ritem_apply;
				if (!q.apply)
					q.apply = "main";
				q.angle = ss.ritem_angle_corners;
				q.corners_main = ss.ritem_main_apply_corners;
				if (!q.corners_main || q.corners_main.length < 4)
					q.corners_main = new Array(true, 1, 1, 1);
				q.corners_sub = ss.ritem_sub_apply_corners;
				if (!q.corners_sub || q.corners_sub.length < 4)
					q.corners_sub = new Array(true, 1, 1, 1);
				q.sactive = false;
				if (ss.ritem_show_on_actives)
					q.sactive = true;
				q.opacity = ss.ritem_opacity;
				if (q.opacity && q.opacity != 1) {
					var addf = "";
					if (window.showHelp)
						addf = "filter:alpha(opacity=" + (q.opacity * 100)
								+ ");";
					q.opacity = "opacity:" + q.opacity + ";" + addf;
				} else
					q.opacity = "";
				qm_ritem_add_rounds(a);
			}
		}
	};
	function qm_ritem_a_hide(a) {
		if (a.idiv.hasritem && qmad.ritem.sactive)
			a.idiv.hasritem.style.left = "-10000px";
	};
	function qm_ritem_a(a) {
		if (a)
			qmad.ritem.a = a;
		else
			a = qmad.ritem.a;
		if (a.idiv.hasritem && qmad.ritem.sactive)
			a.idiv.hasritem.style.left = a.idiv.offsetLeft + "px";
		if (a.ritemfixed)
			return;
		var aa = a.childNodes;
		for ( var i = 0; i < aa.length; i++) {
			var b;
			if (b = aa[i].hasritem) {
				if (!aa[i].offsetWidth) {
					setTimeout("qm_ritem_a()", 10);
					return;
				} else {
					b.style.top = aa[i].offsetTop + "px";
					b.style.left = aa[i].offsetLeft + "px";
					b.style.width = aa[i].offsetWidth + "px";
					a.ritemfixed = 1;
				}
			}
		}
	};
	function qm_ritem_add_rounds(a) {
		var q = qmad.ritem;
		var atags, ist, isd, isp, gom, gos;
		if (q.apply.indexOf("titles") + 1)
			ist = true;
		if (q.apply.indexOf("dividers") + 1)
			isd = true;
		if (q.apply.indexOf("parents") + 1)
			isp = true;
		if (q.apply.indexOf("sub") + 1)
			gos = true;
		if (q.apply.indexOf("main") + 1)
			gom = true;
		atags = a.childNodes;
		for ( var k = 0; k < atags.length; k++) {
			if ((atags[k].tagName != "SPAN" && atags[k].tagName != "A")
					|| (q.sactive && !atags[k].cdiv))
				continue;
			var ism = qm_a(atags[k][qp]);
			if ((isd && atags[k].className.indexOf("qmdivider") + 1)
					|| (ist && atags[k].className.indexOf("qmtitle") + 1)
					|| (gom && ism && atags[k].tagName == "A")
					|| (atags[k].className.indexOf("qmrounditem") + 1)
					|| (gos && !ism && atags[k].tagName == "A")
					|| (isp && atags[k].cdiv)) {
				var f = document.createElement("SPAN");
				f.className = "qmritem";
				f.setAttribute("qmvbefore", 1);
				var fs = f.style;
				fs.position = "absolute";
				fs.display = "block";
				fs.top = atags[k].offsetTop + "px";
				fs.left = atags[k].offsetLeft + "px";
				fs.width = atags[k].offsetWidth + "px";
				if (q.sactive && atags[k].cdiv.style.visibility != "inherit")
					fs.left = "-10000px";
				var size = q.size;
				q.mid = parseInt(size / 2);
				q.ps = new Array(size + 1);
				var t2 = 0;
				q.osize = q.size;
				if (!q.angle) {
					for ( var i = 0; i <= size; i++) {
						if (i == q.mid)
							t2 = 0;
						q.ps[i] = t2;
						t2 += Math.abs(q.mid - i) + 1;
					}
					q.osize = 1;
				}
				var fi = "";
				var ctype = "main";
				if (!ism)
					ctype = "sub";
				for ( var i = 0; i < size; i++)
					fi += qm_ritem_get_span(size, i, 1, ctype);
				var cn = atags[k].cloneNode(true);
				var cns = cn.getElementsByTagName("SPAN");
				for ( var l = 0; l < cns.length; l++) {
					if (cns[l].getAttribute("isibulletcss")
							|| cns[l].getAttribute("isibullet"))
						cn.removeChild(cns[l]);
				}
				fi += '<span class="qmritemcontent" style="display:block;border-style:solid;border-width:0px 1px 0px 1px;'+q.opacity+'">'
						+ cn.innerHTML + '</span>';
				for ( var i = size - 1; i >= 0; i--)
					fi += qm_ritem_get_span(size, i, null, ctype);
				f.innerHTML = fi;
				f = atags[k].insertBefore(f, atags[k].firstChild);
				atags[k].hasritem = f;
			}
			if (atags[k].cdiv)
				new qm_ritem_add_rounds(atags[k].cdiv);
		}
	};
	function qm_ritem_get_span(size, i, top, ctype) {
		var q = qmad.ritem;
		var mlmr;
		if (i == 0) {
			var mo = q.ps[size] + q.mid;
			if (q.angle)
				mo = size - i;
			var fs = "";
			if (qmad.str)
				fs = "&nbsp;";
			mlmr = qm_ritem_get_corners(mo, null, top, ctype);
			return '<span style="border-width:1px 0px 0px 0px;border-style:solid;display:block;font-size:1px;overflow:hidden;line-height:0px;height:0px;margin-left:'+mlmr[0]+'px;margin-right:'+mlmr[1]+'px;'+q.opacity+'">'
					+ fs + '</span>';
		} else {
			var md = size - (i);
			var ih = 1;
			var bs = 1;
			if (!q.angle) {
				if (i >= q.mid)
					ih = Math.abs(q.mid - i) + 1;
				else {
					bs = Math.abs(q.mid - i) + 1;
					md = q.ps[size - i] + q.mid;
				}
				if (top)
					q.osize += ih;
			}
			mlmr = qm_ritem_get_corners(md, bs, top, ctype);
			return '<span style="border-width:0px '+mlmr[3]+'px 0px '+mlmr[2]+'px;border-style:solid;display:block;overflow:hidden;font-size:1px;line-height:0px;height:'+ih+'px;margin-left:'+mlmr[0]+'px;margin-right:'+mlmr[1]+'px;'+q.opacity+'"></span>';
		}
	};
	function qm_ritem_get_corners(mval, bval, top, ctype) {
		var q = qmad.ritem;
		var ml = mval;
		var mr = mval;
		var bl = bval;
		var br = bval;
		if (top) {
			if (!q["corners_" + ctype][0]) {
				ml = 0;
				bl = 1;
			}
			if (!q["corners_" + ctype][1]) {
				mr = 0;
				br = 1;
			}
		} else {
			if (!q["corners_" + ctype][2]) {
				mr = 0;
				br = 1;
			}
			if (!q["corners_" + ctype][3]) {
				ml = 0;
				bl = 1;
			}
		}
		return new Array(ml, mr, bl, br);
	}/*     */