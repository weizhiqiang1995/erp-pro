/*!
 * FullCalendar v1.6.4
 * Docs & License: http://arshaw.com/fullcalendar/
 * (c) 2013 Adam Shaw
 */

/*
 * Use fullcalendar.css for basic styling.
 * For event drag & drop, requires jQuery UI draggable.
 * For event resizing, requires jQuery UI resizable.
 */
 
layui.define(["jquery"], function(exports) {
	var jQuery = layui.jquery;
	(function($) {
		(function($, undefined) {
		;;
		
		var defaults = {
			// display
			defaultView: 'month',
			aspectRatio: 1.35,
			header: {
				left: 'title',
				center: '',
				right: 'today prev,next'
			},
			weekends: true,
			weekNumbers: false,
			weekNumberCalculation: 'iso',
			weekNumberTitle: 'W',
			// editing
			//editable: false,
			//disableDragging: false,
			//disableResizing: false,
			allDayDefault: true,
			ignoreTimezone: true,
			// event ajax
			lazyFetching: true,
			startParam: 'start',
			endParam: 'end',
			// time formats
			titleFormat: {
				month: 'yyyy年MMMM',
				week: "MMMd日[yyyy年]{' ~ 'MMMd日yyyy年}",
				day: 'yyyy年MMMd日, dddd'
			},
			columnFormat: {
				month: 'ddd',
				week: 'ddd M/d',
				day: 'dddd M/d'
			},
			timeFormat: { // for event elements
				'': 'HH:mmt' // default
			},
			// locale
			isRTL: false,
			firstDay: 0,
			monthNames: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
			monthNamesShort: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
			dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
			dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
			buttonText: {
				prev: "<span class='fc-text-arrow'>&lsaquo;</span>",
				next: "<span class='fc-text-arrow'>&rsaquo;</span>",
				prevYear: "<span class='fc-text-arrow'>&laquo;</span>",
				nextYear: "<span class='fc-text-arrow'>&raquo;</span>",
				today: '今天',
				month: '按月查看',
				week: '按周查看',
				day: '按天查看'
			},
			// jquery-ui theming
			theme: false,
			buttonIcons: {
				prev: 'circle-triangle-w',
				next: 'circle-triangle-e'
			},
			//selectable: false,
			unselectAuto: true,
			dropAccept: '*',
			handleWindowResize: true
		};
		// right-to-left defaults
		var rtlDefaults = {
			header: {
				left: 'next,prev today',
				center: '',
				right: 'title'
			},
			buttonText: {
				prev: "<span class='fc-text-arrow'>&rsaquo;</span>",
				next: "<span class='fc-text-arrow'>&lsaquo;</span>",
				prevYear: "<span class='fc-text-arrow'>&raquo;</span>",
				nextYear: "<span class='fc-text-arrow'>&laquo;</span>"
			},
			buttonIcons: {
				prev: 'circle-triangle-e',
				next: 'circle-triangle-w'
			}
		};
		;;
		var fc = $.fullCalendar = { version: "1.6.4" };
		var fcViews = fc.views = {};
		$.fn.fullCalendar = function(options) {
			// method calling
			if (typeof options == 'string') {
				var args = Array.prototype.slice.call(arguments, 1);
				var res;
				this.each(function() {
					var calendar = $.data(this, 'fullCalendar');
					if (calendar && $.isFunction(calendar[options])) {
						var r = calendar[options].apply(calendar, args);
						if (res === undefined) {
							res = r;
						}
						if (options == 'destroy') {
							$.removeData(this, 'fullCalendar');
						}
					}
				});
				if (res !== undefined) {
					return res;
				}
				return this;
			}
			options = options || {};
			// would like to have this logic in EventManager, but needs to happen before options are recursively extended
			var eventSources = options.eventSources || [];
			delete options.eventSources;
			if (options.events) {
				eventSources.push(options.events);
				delete options.events;
			}
			options = $.extend(true, {},
				defaults,
				(options.isRTL || options.isRTL===undefined && defaults.isRTL) ? rtlDefaults : {},
				options
			);
			this.each(function(i, _element) {
				var element = $(_element);
				var calendar = new Calendar(element, options, eventSources);
				element.data('fullCalendar', calendar); 
				calendar.render();
			});
			return this;
		};
		// function for adding/overriding defaults
		function setDefaults(d) {
			$.extend(true, defaults, d);
		}
		;;
		function Calendar(element, options, eventSources) {
			var t = this;
			// exports
			t.options = options;
			t.render = render;
			t.destroy = destroy;
			t.refetchEvents = refetchEvents;
			t.reportEvents = reportEvents;
			t.reportEventChange = reportEventChange;
			t.rerenderEvents = rerenderEvents;
			t.changeView = changeView;
			t.select = select;
			t.unselect = unselect;
			t.prev = prev;
			t.next = next;
			t.prevYear = prevYear;
			t.nextYear = nextYear;
			t.today = today;
			t.gotoDate = gotoDate;
			t.incrementDate = incrementDate;
			t.formatDate = function(format, date) { return formatDate(format, date, options) };
			t.formatDates = function(format, date1, date2) { return formatDates(format, date1, date2, options) };
			t.getDate = getDate;
			t.getView = getView;
			t.option = option;
			t.trigger = trigger;
			// imports
			EventManager.call(t, options, eventSources);
			var isFetchNeeded = t.isFetchNeeded;
			var fetchEvents = t.fetchEvents;
			// locals
			var _element = element[0];
			var header;
			var headerElement;
			var content;
			var tm; // for making theme classes
			var currentView;
			var elementOuterWidth;
			var suggestedViewHeight;
			var resizeUID = 0;
			var ignoreWindowResize = 0;
			var date = new Date();
			var events = [];
			var _dragElement;
			setYMD(date, options.year, options.month, options.date);
			function render(inc) {
				if (!content) {
					initialRender();
				}
				else if (elementVisible()) {
					calcSize();
					_renderView(inc);
				}
			}
			function initialRender() {
				tm = options.theme ? 'ui' : 'fc';
				element.addClass('fc');
				if (options.isRTL) {
					element.addClass('fc-rtl');
				} else {
					element.addClass('fc-ltr');
				}
				if (options.theme) {
					element.addClass('ui-widget');
				}
				content = $("<div class='fc-content' style='position:relative'/>").prependTo(element);
				header = new Header(t, options);
				headerElement = header.render();
				if (headerElement) {
					element.prepend(headerElement);
				}
				changeView(options.defaultView);
				if (options.handleWindowResize) {
					$(window).resize(windowResize);
				}
				if (!bodyVisible()) {
					lateRender();
				}
			}
			function lateRender() {
				setTimeout(function() { // IE7 needs this so dimensions are calculated correctly
					if (!currentView.start && bodyVisible()) { // !currentView.start makes sure this never happens more than once
						renderView();
					}
				}, 0);
			}
			function destroy() {
				if (currentView) {
					trigger('viewDestroy', currentView, currentView, currentView.element);
					currentView.triggerEventDestroy();
				}
				$(window).unbind('resize', windowResize);
				header.destroy();
				content.remove();
				element.removeClass('fc fc-rtl ui-widget');
			}
			function elementVisible() {
				return element.is(':visible');
			}
			function bodyVisible() {
				return $('body').is(':visible');
			}
			function changeView(newViewName) {
				if (!currentView || newViewName != currentView.name) {
					_changeView(newViewName);
				}
			}
			function _changeView(newViewName) {
				ignoreWindowResize++;
				if (currentView) {
					trigger('viewDestroy', currentView, currentView, currentView.element);
					unselect();
					currentView.triggerEventDestroy(); // trigger 'eventDestroy' for each event
					freezeContentHeight();
					currentView.element.remove();
					header.deactivateButton(currentView.name);
				}
				header.activateButton(newViewName);
				currentView = new fcViews[newViewName]($("<div class='fc-view fc-view-" + newViewName + "' style='position:relative'/>").appendTo(content), t);
				renderView();
				unfreezeContentHeight();
				ignoreWindowResize--;
			}
			function renderView(inc) {
				if (!currentView.start || inc || date < currentView.start || date >= currentView.end ) {
					if (elementVisible()) {
						_renderView(inc);
					}
				}
			}
			function _renderView(inc) { // assumes elementVisible
				ignoreWindowResize++;
				if (currentView.start) { // already been rendered?
					trigger('viewDestroy', currentView, currentView, currentView.element);
					unselect();
					clearEvents();
				}
				freezeContentHeight();
				currentView.render(date, inc || 0); // the view's render method ONLY renders the skeleton, nothing else
				setSize();
				unfreezeContentHeight();
				(currentView.afterRender || noop)();
				updateTitle();
				updateTodayButton();
				trigger('viewRender', currentView, currentView, currentView.element);
				currentView.trigger('viewDisplay', _element); // deprecated
				ignoreWindowResize--;
				getAndRenderEvents();
			}
			function updateSize() {
				if (elementVisible()) {
					unselect();
					clearEvents();
					calcSize();
					setSize();
					renderEvents();
				}
			}
			function calcSize() { // assumes elementVisible
				if (options.contentHeight) {
					suggestedViewHeight = options.contentHeight;
				}
				else if (options.height) {
					suggestedViewHeight = options.height - (headerElement ? headerElement.height() : 0) - vsides(content);
				}
				else {
					suggestedViewHeight = Math.round(content.width() / Math.max(options.aspectRatio, .5));
				}
			}
			function setSize() { // assumes elementVisible
				if (suggestedViewHeight === undefined) {
					calcSize(); // for first time
				}
				ignoreWindowResize++;
				currentView.setHeight(suggestedViewHeight);
				currentView.setWidth(content.width());
				ignoreWindowResize--;
				elementOuterWidth = element.outerWidth();
			}
			function windowResize() {
				if (!ignoreWindowResize) {
					if (currentView.start) { // view has already been rendered
						var uid = ++resizeUID;
						setTimeout(function() { // add a delay
							if (uid == resizeUID && !ignoreWindowResize && elementVisible()) {
								if (elementOuterWidth != (elementOuterWidth = element.outerWidth())) {
									ignoreWindowResize++; // in case the windowResize callback changes the height
									updateSize();
									currentView.trigger('windowResize', _element);
									ignoreWindowResize--;
								}
							}
						}, 200);
					}else{
						lateRender();
					}
				}
			}
			function refetchEvents() { // can be called as an API method
				clearEvents();
				fetchAndRenderEvents();
			}
			function rerenderEvents(modifiedEventID) { // can be called as an API method
				clearEvents();
				renderEvents(modifiedEventID);
			}
			function renderEvents(modifiedEventID) {
				if (elementVisible()) {
					currentView.setEventData(events);
					currentView.renderEvents(events, modifiedEventID); // actually render the DOM elements
					currentView.trigger('eventAfterAllRender');
				}
			}
			function clearEvents() {
				currentView.triggerEventDestroy(); // trigger 'eventDestroy' for each event
				currentView.clearEvents(); // actually remove the DOM elements
				currentView.clearEventData();
			}
			function getAndRenderEvents() {
				if (!options.lazyFetching || isFetchNeeded(currentView.visStart, currentView.visEnd)) {
					fetchAndRenderEvents();
				}
				else {
					renderEvents();
				}
			}
			function fetchAndRenderEvents() {
				fetchEvents(currentView.visStart, currentView.visEnd);
			}
			function reportEvents(_events) {
				events = _events;
				renderEvents();
			}
			function reportEventChange(eventID) {
				rerenderEvents(eventID);
			}
			function updateTitle() {
				header.updateTitle(currentView.title);
			}
			function updateTodayButton() {
				var today = new Date();
				if (today >= currentView.start && today < currentView.end) {
					header.disableButton('today');
				}
				else {
					header.enableButton('today');
				}
			}
			function select(start, end, allDay) {
				currentView.select(start, end, allDay===undefined ? true : allDay);
			}
			function unselect() { // safe to be called before renderView
				if (currentView) {
					currentView.unselect();
				}
			}
			function prev() {
				renderView(-1);
			}
			function next() {
				renderView(1);
			}
			function prevYear() {
				addYears(date, -1);
				renderView();
			}
			function nextYear() {
				addYears(date, 1);
				renderView();
			}
			function today() {
				date = new Date();
				renderView();
			}
			function gotoDate(year, month, dateOfMonth) {
				if (year instanceof Date) {
					date = cloneDate(year); // provided 1 argument, a Date
				}else{
					setYMD(date, year, month, dateOfMonth);
				}
				renderView();
			}
			function incrementDate(years, months, days) {
				if (years !== undefined) {
					addYears(date, years);
				}
				if (months !== undefined) {
					addMonths(date, months);
				}
				if (days !== undefined) {
					addDays(date, days);
				}
				renderView();
			}
			function getDate() {
				return cloneDate(date);
			}
			function freezeContentHeight() {
				content.css({
					width: '100%',
					height: content.height(),
					overflow: 'hidden'
				});
			}
			function unfreezeContentHeight() {
				content.css({
					width: '',
					height: '',
					overflow: ''
				});
			}
			function getView() {
				return currentView;
			}
			function option(name, value) {
				if (value === undefined) {
					return options[name];
				}
				if (name == 'height' || name == 'contentHeight' || name == 'aspectRatio') {
					options[name] = value;
					updateSize();
				}
			}
			function trigger(name, thisObj) {
				if (options[name]) {
					return options[name].apply(
						thisObj || _element,
						Array.prototype.slice.call(arguments, 2)
					);
				}
			}
			if (options.droppable) {
				$(document).bind('dragstart', function(ev, ui) {
					var _e = ev.target;
					var e = $(_e);
					if (!e.parents('.fc').length) { // not already inside a calendar
						var accept = options.dropAccept;
						if ($.isFunction(accept) ? accept.call(_e, e) : e.is(accept)) {
							_dragElement = _e;
							currentView.dragStart(_dragElement, ev, ui);
						}
					}
				}).bind('dragstop', function(ev, ui) {
					if (_dragElement) {
						currentView.dragStop(_dragElement, ev, ui);
						_dragElement = null;
					}
				});
			}
		}
		;;
		function Header(calendar, options) {
			var t = this;
			t.render = render;
			t.destroy = destroy;
			t.updateTitle = updateTitle;
			t.activateButton = activateButton;
			t.deactivateButton = deactivateButton;
			t.disableButton = disableButton;
			t.enableButton = enableButton;
			var element = $([]);
			var tm;
			function render() {
				tm = options.theme ? 'ui' : 'fc';
				var sections = options.header;
				if (sections) {
					element = $("<table class='fc-header' style='width:100%'/>").append($("<tr/>").append(renderSection('left')).append(renderSection('center')).append(renderSection('right')));
					return element;
				}
			}
			function destroy() {
				element.remove();
			}
			function renderSection(position) {
				var e = $("<td class='fc-header-" + position + "'/>");
				var buttonStr = options.header[position];
				if (buttonStr) {
					$.each(buttonStr.split(' '), function(i) {
						if (i > 0) {
							e.append("<span class='fc-header-space'/>");
						}
						var prevButton;
						$.each(this.split(','), function(j, buttonName) {
							if (buttonName == 'title') {
								e.append("<span class='fc-header-title'><h2>&nbsp;</h2></span>");
								if (prevButton) {
									prevButton.addClass(tm + '-corner-right');
								}
								prevButton = null;
							}else{
								var buttonClick;
								if (calendar[buttonName]) {
									buttonClick = calendar[buttonName]; // calendar method
								}
								else if (fcViews[buttonName]) {
									buttonClick = function() {
										button.removeClass(tm + '-state-hover'); // forget why
										calendar.changeView(buttonName);
									};
								}
								if (buttonClick) {
									var icon = options.theme ? smartProperty(options.buttonIcons, buttonName) : null; // why are we using smartProperty here?
									var text = smartProperty(options.buttonText, buttonName); // why are we using smartProperty here?
									var button = $(
										"<span class='fc-button fc-button-" + buttonName + " " + tm + "-state-default'>" +
											(icon ?
												"<span class='fc-icon-wrap'>" +
													"<span class='ui-icon ui-icon-" + icon + "'/>" +
												"</span>" :
												text
												) +
										"</span>"
										).click(function() {
											if (!button.hasClass(tm + '-state-disabled')) {
												buttonClick();
											}
										}).mousedown(function() {
											button
												.not('.' + tm + '-state-active')
												.not('.' + tm + '-state-disabled')
												.addClass(tm + '-state-down');
										}).mouseup(function() {
											button.removeClass(tm + '-state-down');
										}).hover(
											function() {
												button
													.not('.' + tm + '-state-active')
													.not('.' + tm + '-state-disabled')
													.addClass(tm + '-state-hover');
											},
											function() {
												button
													.removeClass(tm + '-state-hover')
													.removeClass(tm + '-state-down');
											}
										).appendTo(e);
									disableTextSelection(button);
									if (!prevButton) {
										button.addClass(tm + '-corner-left');
									}
									prevButton = button;
								}
							}
						});
						if (prevButton) {
							prevButton.addClass(tm + '-corner-right');
						}
					});
				}
				return e;
			}
			function updateTitle(html) {
				element.find('h2')
					.html(html);
			}
			function activateButton(buttonName) {
				element.find('span.fc-button-' + buttonName)
					.addClass(tm + '-state-active');
			}
			function deactivateButton(buttonName) {
				element.find('span.fc-button-' + buttonName)
					.removeClass(tm + '-state-active');
			}
			function disableButton(buttonName) {
				element.find('span.fc-button-' + buttonName)
					.addClass(tm + '-state-disabled');
			}
			function enableButton(buttonName) {
				element.find('span.fc-button-' + buttonName)
					.removeClass(tm + '-state-disabled');
			}
		}
		;;
		fc.sourceNormalizers = [];
		fc.sourceFetchers = [];
		var ajaxDefaults = {
			dataType: 'json',
			cache: false
		};
		var eventGUID = 1;
		function EventManager(options, _sources) {
			var t = this;
			t.isFetchNeeded = isFetchNeeded;
			t.fetchEvents = fetchEvents;
			t.addEventSource = addEventSource;
			t.removeEventSource = removeEventSource;
			t.updateEvent = updateEvent;
			t.renderEvent = renderEvent;
			t.removeEvents = removeEvents;
			t.clientEvents = clientEvents;
			t.normalizeEvent = normalizeEvent;
			var trigger = t.trigger;
			var getView = t.getView;
			var reportEvents = t.reportEvents;
			var stickySource = { events: [] };
			var sources = [ stickySource ];
			var rangeStart, rangeEnd;
			var currentFetchID = 0;
			var pendingSourceCnt = 0;
			var loadingLevel = 0;
			var cache = [];
			for (var i=0; i<_sources.length; i++) {
				_addEventSource(_sources[i]);
			}
			/* Fetching -----------------------------------------------------------------------------*/
			function isFetchNeeded(start, end) {
				return !rangeStart || start < rangeStart || end > rangeEnd;
			}
			function fetchEvents(start, end) {
				rangeStart = start;
				rangeEnd = end;
				cache = [];
				var fetchID = ++currentFetchID;
				var len = sources.length;
				pendingSourceCnt = len;
				for (var i=0; i<len; i++) {
					fetchEventSource(sources[i], fetchID);
				}
			}
			function fetchEventSource(source, fetchID) {
				_fetchEventSource(source, function(events) {
					if (fetchID == currentFetchID) {
						if (events) {
							if (options.eventDataTransform) {
								events = $.map(events, options.eventDataTransform);
							}
							if (source.eventDataTransform) {
								events = $.map(events, source.eventDataTransform);
							}
							for (var i=0; i<events.length; i++) {
								events[i].source = source;
								normalizeEvent(events[i]);
							}
							cache = cache.concat(events);
						}
						pendingSourceCnt--;
						if (!pendingSourceCnt) {
							reportEvents(cache);
						}
					}
				});
			}
			function _fetchEventSource(source, callback) {
				var i;
				var fetchers = fc.sourceFetchers;
				var res;
				for (i=0; i<fetchers.length; i++) {
					res = fetchers[i](source, rangeStart, rangeEnd, callback);
					if (res === true) {
						return;
					} else if (typeof res == 'object') {
						_fetchEventSource(res, callback);
						return;
					}
				}
				var events = source.events;
				if (events) {
					if ($.isFunction(events)) {
						pushLoading();
						events(cloneDate(rangeStart), cloneDate(rangeEnd), function(events) {
							callback(events);
							popLoading();
						});
					}
					else if ($.isArray(events)) {
						callback(events);
					}
					else {
						callback();
					}
				}else{
					var url = source.url;
					if (url) {
						var success = source.success;
						var error = source.error;
						var complete = source.complete;
						var customData;
						if ($.isFunction(source.data)) {
							customData = source.data();
						} else {
							customData = source.data;
						}
						var data = $.extend({}, customData || {});
						var startParam = firstDefined(source.startParam, options.startParam);
						var endParam = firstDefined(source.endParam, options.endParam);
						if (startParam) {
							data[startParam] = Math.round(+rangeStart / 1000);
						}
						if (endParam) {
							data[endParam] = Math.round(+rangeEnd / 1000);
						}
						pushLoading();
						$.ajax($.extend({}, ajaxDefaults, source, {
							data: data,
							success: function(events) {
								events = events || [];
								var res = applyAll(success, this, arguments);
								if ($.isArray(res)) {
									events = res;
								}
								callback(events);
							},
							error: function() {
								applyAll(error, this, arguments);
								callback();
							},
							complete: function() {
								applyAll(complete, this, arguments);
								popLoading();
							}
						}));
					}else{
						callback();
					}
				}
			}
			/* Sources -----------------------------------------------------------------------------*/
			function addEventSource(source) {
				source = _addEventSource(source);
				if (source) {
					pendingSourceCnt++;
					fetchEventSource(source, currentFetchID); // will eventually call reportEvents
				}
			}
			function _addEventSource(source) {
				if ($.isFunction(source) || $.isArray(source)) {
					source = { events: source };
				}
				else if (typeof source == 'string') {
					source = { url: source };
				}
				if (typeof source == 'object') {
					normalizeSource(source);
					sources.push(source);
					return source;
				}
			}
			function removeEventSource(source) {
				sources = $.grep(sources, function(src) {
					return !isSourcesEqual(src, source);
				});
				cache = $.grep(cache, function(e) {
					return !isSourcesEqual(e.source, source);
				});
				reportEvents(cache);
			}
			/* Manipulation -----------------------------------------------------------------------------*/
			function updateEvent(event) { // update an existing event
				var i, len = cache.length, e,
					defaultEventEnd = getView().defaultEventEnd, // getView???
					startDelta = event.start - event._start,
					endDelta = event.end ? (event.end - (event._end || defaultEventEnd(event))) : 0;
				for (i=0; i<len; i++) {
					e = cache[i];
					if (e._id == event._id && e != event) {
						e.start = new Date(+e.start + startDelta);
						if (event.end) {
							if (e.end) {
								e.end = new Date(+e.end + endDelta);
							}else{
								e.end = new Date(+defaultEventEnd(e) + endDelta);
							}
						}else{
							e.end = null;
						}
						e.title = event.title;
						e.url = event.url;
						e.allDay = event.allDay;
						e.className = event.className;
						e.editable = event.editable;
						e.color = event.color;
						e.backgroundColor = event.backgroundColor;
						e.borderColor = event.borderColor;
						e.textColor = event.textColor;
						normalizeEvent(e);
					}
				}
				normalizeEvent(event);
				reportEvents(cache);
			}
			
			function renderEvent(event, stick) {
				normalizeEvent(event);
				if (!event.source) {
					if (stick) {
						stickySource.events.push(event);
						event.source = stickySource;
					}
					cache.push(event);
				}
				reportEvents(cache);
			}
			
			function removeEvents(filter) {
				if (!filter) { // remove all
					cache = [];
					for (var i=0; i<sources.length; i++) {
						if ($.isArray(sources[i].events)) {
							sources[i].events = [];
						}
					}
				}else{
					if (!$.isFunction(filter)) { // an event ID
						var id = filter + '';
						filter = function(e) {
							return e._id == id;
						};
					}
					cache = $.grep(cache, filter, true);
					// remove events from array sources
					for (var i=0; i<sources.length; i++) {
						if ($.isArray(sources[i].events)) {
							sources[i].events = $.grep(sources[i].events, filter, true);
						}
					}
				}
				reportEvents(cache);
			}
			
			function clientEvents(filter) {
				if ($.isFunction(filter)) {
					return $.grep(cache, filter);
				}
				else if (filter) { // an event ID
					filter += '';
					return $.grep(cache, function(e) {
						return e._id == filter;
					});
				}
				return cache; // else, return all
			}
			
			/* Loading State -----------------------------------------------------------------------------*/
			function pushLoading() {
				if (!loadingLevel++) {
					trigger('loading', null, true, getView());
				}
			}
			
			function popLoading() {
				if (!--loadingLevel) {
					trigger('loading', null, false, getView());
				}
			}
			
			/* Event Normalization -----------------------------------------------------------------------------*/
			function normalizeEvent(event) {
				var source = event.source || {};
				var ignoreTimezone = firstDefined(source.ignoreTimezone, options.ignoreTimezone);
				event._id = event._id || (event.id === undefined ? '_fc' + eventGUID++ : event.id + '');
				if (event.date) {
					if (!event.start) {
						event.start = event.date;
					}
					delete event.date;
				}
				event._start = cloneDate(event.start = parseDate(event.start, ignoreTimezone));
				event.end = parseDate(event.end, ignoreTimezone);
				if (event.end && event.end <= event.start) {
					event.end = null;
				}
				event._end = event.end ? cloneDate(event.end) : null;
				if (event.allDay === undefined) {
					event.allDay = firstDefined(source.allDayDefault, options.allDayDefault);
				}
				if (event.className) {
					if (typeof event.className == 'string') {
						event.className = event.className.split(/\s+/);
					}
				}else{
					event.className = [];
				}
			}
			/* Utils ------------------------------------------------------------------------------*/
			function normalizeSource(source) {
				if (source.className) {
					if (typeof source.className == 'string') {
						source.className = source.className.split(/\s+/);
					}
				}else{
					source.className = [];
				}
				var normalizers = fc.sourceNormalizers;
				for (var i=0; i<normalizers.length; i++) {
					normalizers[i](source);
				}
			}
			
			function isSourcesEqual(source1, source2) {
				return source1 && source2 && getSourcePrimitive(source1) == getSourcePrimitive(source2);
			}
			
			function getSourcePrimitive(source) {
				return ((typeof source == 'object') ? (source.events || source.url) : '') || source;
			}
		};;
		
		fc.addDays = addDays;
		fc.cloneDate = cloneDate;
		fc.parseDate = parseDate;
		fc.parseISO8601 = parseISO8601;
		fc.parseTime = parseTime;
		fc.formatDate = formatDate;
		fc.formatDates = formatDates;
		
		/* Date Math -----------------------------------------------------------------------------*/
		var dayIDs = ['sun', 'mon', 'tue', 'wed', 'thu', 'fri', 'sat'],
			DAY_MS = 86400000,
			HOUR_MS = 3600000,
			MINUTE_MS = 60000;
		
		function addYears(d, n, keepTime) {
			d.setFullYear(d.getFullYear() + n);
			if (!keepTime) {
				clearTime(d);
			}
			return d;
		}
		
		function addMonths(d, n, keepTime) { // prevents day overflow/underflow
			if (+d) { // prevent infinite looping on invalid dates
				var m = d.getMonth() + n,
					check = cloneDate(d);
				check.setDate(1);
				check.setMonth(m);
				d.setMonth(m);
				if (!keepTime) {
					clearTime(d);
				}
				while (d.getMonth() != check.getMonth()) {
					d.setDate(d.getDate() + (d < check ? 1 : -1));
				}
			}
			return d;
		}
		
		function addDays(d, n, keepTime) { // deals with daylight savings
			if (+d) {
				var dd = d.getDate() + n,
					check = cloneDate(d);
				check.setHours(9); // set to middle of day
				check.setDate(dd);
				d.setDate(dd);
				if (!keepTime) {
					clearTime(d);
				}
				fixDate(d, check);
			}
			return d;
		}
		
		function fixDate(d, check) { // force d to be on check's YMD, for daylight savings purposes
			if (+d) { // prevent infinite looping on invalid dates
				while (d.getDate() != check.getDate()) {
					d.setTime(+d + (d < check ? 1 : -1) * HOUR_MS);
				}
			}
		}
		
		function addMinutes(d, n) {
			d.setMinutes(d.getMinutes() + n);
			return d;
		}
		
		function clearTime(d) {
			d.setHours(0);
			d.setMinutes(0);
			d.setSeconds(0); 
			d.setMilliseconds(0);
			return d;
		}
		
		function cloneDate(d, dontKeepTime) {
			if (dontKeepTime) {
				return clearTime(new Date(+d));
			}
			return new Date(+d);
		}
		
		function zeroDate() { // returns a Date with time 00:00:00 and dateOfMonth=1
			var i=0, d;
			do {
				d = new Date(1970, i++, 1);
			} while (d.getHours()); // != 0
			return d;
		}
		
		function dayDiff(d1, d2) { // d1 - d2
			return Math.round((cloneDate(d1, true) - cloneDate(d2, true)) / DAY_MS);
		}
		
		function setYMD(date, y, m, d) {
			if (y !== undefined && y != date.getFullYear()) {
				date.setDate(1);
				date.setMonth(0);
				date.setFullYear(y);
			}
			if (m !== undefined && m != date.getMonth()) {
				date.setDate(1);
				date.setMonth(m);
			}
			if (d !== undefined) {
				date.setDate(d);
			}
		}
		
		/* Date Parsing -----------------------------------------------------------------------------*/
		function parseDate(s, ignoreTimezone) { // ignoreTimezone defaults to true
			if (typeof s == 'object') { // already a Date object
				return s;
			}
			if (typeof s == 'number') { // a UNIX timestamp
				return new Date(s * 1000);
			}
			if (typeof s == 'string') {
				if (s.match(/^\d+(\.\d+)?$/)) { // a UNIX timestamp
					return new Date(parseFloat(s) * 1000);
				}
				if (ignoreTimezone === undefined) {
					ignoreTimezone = true;
				}
				return parseISO8601(s, ignoreTimezone) || (s ? new Date(s) : null);
			}
			return null;
		}
		
		function parseISO8601(s, ignoreTimezone) { // ignoreTimezone defaults to false
			// derived from http://delete.me.uk/2005/03/iso8601.html
			var m = s.match(/^([0-9]{4})(-([0-9]{2})(-([0-9]{2})([T ]([0-9]{2}):([0-9]{2})(:([0-9]{2})(\.([0-9]+))?)?(Z|(([-+])([0-9]{2})(:?([0-9]{2}))?))?)?)?)?$/);
			if (!m) {
				return null;
			}
			var date = new Date(m[1], 0, 1);
			if (ignoreTimezone || !m[13]) {
				var check = new Date(m[1], 0, 1, 9, 0);
				if (m[3]) {
					date.setMonth(m[3] - 1);
					check.setMonth(m[3] - 1);
				}
				if (m[5]) {
					date.setDate(m[5]);
					check.setDate(m[5]);
				}
				fixDate(date, check);
				if (m[7]) {
					date.setHours(m[7]);
				}
				if (m[8]) {
					date.setMinutes(m[8]);
				}
				if (m[10]) {
					date.setSeconds(m[10]);
				}
				if (m[12]) {
					date.setMilliseconds(Number("0." + m[12]) * 1000);
				}
				fixDate(date, check);
			}else{
				date.setUTCFullYear(
					m[1],
					m[3] ? m[3] - 1 : 0,
					m[5] || 1
				);
				date.setUTCHours(
					m[7] || 0,
					m[8] || 0,
					m[10] || 0,
					m[12] ? Number("0." + m[12]) * 1000 : 0
				);
				if (m[14]) {
					var offset = Number(m[16]) * 60 + (m[18] ? Number(m[18]) : 0);
					offset *= m[15] == '-' ? 1 : -1;
					date = new Date(+date + (offset * 60 * 1000));
				}
			}
			return date;
		}
		
		function parseTime(s) { // returns minutes since start of day
			if (typeof s == 'number') { // an hour
				return s * 60;
			}
			if (typeof s == 'object') { // a Date object
				return s.getHours() * 60 + s.getMinutes();
			}
			var m = s.match(/(\d+)(?::(\d+))?\s*(\w+)?/);
			if (m) {
				var h = parseInt(m[1], 10);
				if (m[3]) {
					h %= 12;
					if (m[3].toLowerCase().charAt(0) == 'p') {
						h += 12;
					}
				}
				return h * 60 + (m[2] ? parseInt(m[2], 10) : 0);
			}
		}
		
		/* Date Formatting -----------------------------------------------------------------------------*/
		function formatDate(date, format, options) {
			return formatDates(date, null, format, options);
		}
		
		function formatDates(date1, date2, format, options) {
			options = options || defaults;
			var date = date1,
				otherDate = date2,
				i, len = format.length, c,
				i2, formatter,
				res = '';
			for (i=0; i<len; i++) {
				c = format.charAt(i);
				if (c == "'") {
					for (i2=i+1; i2<len; i2++) {
						if (format.charAt(i2) == "'") {
							if (date) {
								if (i2 == i+1) {
									res += "'";
								}else{
									res += format.substring(i+1, i2);
								}
								i = i2;
							}
							break;
						}
					}
				} else if (c == '(') {
					for (i2=i+1; i2<len; i2++) {
						if (format.charAt(i2) == ')') {
							var subres = formatDate(date, format.substring(i+1, i2), options);
							if (parseInt(subres.replace(/\D/, ''), 10)) {
								res += subres;
							}
							i = i2;
							break;
						}
					}
				} else if (c == '[') {
					for (i2=i+1; i2<len; i2++) {
						if (format.charAt(i2) == ']') {
							var subformat = format.substring(i+1, i2);
							var subres = formatDate(date, subformat, options);
							if (subres != formatDate(otherDate, subformat, options)) {
								res += subres;
							}
							i = i2;
							break;
						}
					}
				} else if (c == '{') {
					date = date2;
					otherDate = date1;
				} else if (c == '}') {
					date = date1;
					otherDate = date2;
				} else {
					for (i2=len; i2>i; i2--) {
						if (formatter = dateFormatters[format.substring(i, i2)]) {
							if (date) {
								res += formatter(date, options);
							}
							i = i2 - 1;
							break;
						}
					}
					if (i2 == i) {
						if (date) {
							res += c;
						}
					}
				}
			}
			return res;
		};
		
		var dateFormatters = {
			s	: function(d)	{ return d.getSeconds() },
			ss	: function(d)	{ return zeroPad(d.getSeconds()) },
			m	: function(d)	{ return d.getMinutes() },
			mm	: function(d)	{ return zeroPad(d.getMinutes()) },
			h	: function(d)	{ return d.getHours() % 12 || 12 },
			hh	: function(d)	{ return zeroPad(d.getHours() % 12 || 12) },
			H	: function(d)	{ return d.getHours() },
			HH	: function(d)	{ return zeroPad(d.getHours()) },
			d	: function(d)	{ return d.getDate() },
			dd	: function(d)	{ return zeroPad(d.getDate()) },
			ddd	: function(d, o)	{ return o.dayNamesShort[d.getDay()] },
			dddd: function(d, o)	{ return o.dayNames[d.getDay()] },
			M	: function(d)	{ return d.getMonth() + 1 },
			MM	: function(d)	{ return zeroPad(d.getMonth() + 1) },
			MMM	: function(d, o)	{ return o.monthNamesShort[d.getMonth()] },
			MMMM: function(d, o)	{ return o.monthNames[d.getMonth()] },
			yy	: function(d)	{ return (d.getFullYear()+'').substring(2) },
			yyyy: function(d)	{ return d.getFullYear() },
			t	: function(d)	{ return d.getHours() < 12 ? '上午' : '下午' },
			tt	: function(d)	{ return d.getHours() < 12 ? '上午' : '下午' },
			T	: function(d)	{ return d.getHours() < 12 ? 'A' : 'P' },
			TT	: function(d)	{ return d.getHours() < 12 ? 'AM' : 'PM' },
			u	: function(d)	{ return formatDate(d, "yyyy-MM-dd'T'HH:mm:ss'Z'") },
			S	: function(d)	{
				var date = d.getDate();
				if (date > 10 && date < 20) {
					return 'th';
				}
				return ['st', 'nd', 'rd'][date%10-1] || 'th';
			},
			w   : function(d, o) { // local
				return o.weekNumberCalculation(d);
			},
			W   : function(d) { // ISO
				return iso8601Week(d);
			}
		};
		fc.dateFormatters = dateFormatters;
		
		function iso8601Week(date) {
			var time;
			var checkDate = new Date(date.getTime());
			// Find Thursday of this week starting on Monday
			checkDate.setDate(checkDate.getDate() + 4 - (checkDate.getDay() || 7));
			time = checkDate.getTime();
			checkDate.setMonth(0); // Compare with Jan 1
			checkDate.setDate(1);
			return Math.floor(Math.round((time - checkDate) / 86400000) / 7) + 1;
		};;
		
		fc.applyAll = applyAll;
		
		/* Event Date Math -----------------------------------------------------------------------------*/
		function exclEndDay(event) {
			if (event.end) {
				return _exclEndDay(event.end, event.allDay);
			}else{
				return addDays(cloneDate(event.start), 1);
			}
		}
		
		function _exclEndDay(end, allDay) {
			end = cloneDate(end);
			return allDay || end.getHours() || end.getMinutes() ? addDays(end, 1) : clearTime(end);
		}
		
		/* Event Element Binding -----------------------------------------------------------------------------*/
		function lazySegBind(container, segs, bindHandlers) {
			container.unbind('mouseover').mouseover(function(ev) {
				var parent=ev.target, e,
					i, seg;
				while (parent != this) {
					e = parent;
					parent = parent.parentNode;
				}
				if ((i = e._fci) !== undefined) {
					e._fci = undefined;
					seg = segs[i];
					bindHandlers(seg.event, seg.element, seg);
					$(ev.target).trigger(ev);
				}
				ev.stopPropagation();
			});
		}
		
		/* Element Dimensions -----------------------------------------------------------------------------*/
		function setOuterWidth(element, width, includeMargins) {
			for (var i=0, e; i<element.length; i++) {
				e = $(element[i]);
				e.width(Math.max(0, width - hsides(e, includeMargins)));
			}
		}
		
		function setOuterHeight(element, height, includeMargins) {
			for (var i=0, e; i<element.length; i++) {
				e = $(element[i]);
				e.height(Math.max(0, height - vsides(e, includeMargins)));
			}
		}
		
		function hsides(element, includeMargins) {
			return hpadding(element) + hborders(element) + (includeMargins ? hmargins(element) : 0);
		}
		
		function hpadding(element) {
			return (parseFloat($.css(element[0], 'paddingLeft', true)) || 0) +
			       (parseFloat($.css(element[0], 'paddingRight', true)) || 0);
		}
		
		function hmargins(element) {
			return (parseFloat($.css(element[0], 'marginLeft', true)) || 0) +
			       (parseFloat($.css(element[0], 'marginRight', true)) || 0);
		}
		
		function hborders(element) {
			return (parseFloat($.css(element[0], 'borderLeftWidth', true)) || 0) +
			       (parseFloat($.css(element[0], 'borderRightWidth', true)) || 0);
		}
		
		function vsides(element, includeMargins) {
			return vpadding(element) +  vborders(element) + (includeMargins ? vmargins(element) : 0);
		}
		
		function vpadding(element) {
			return (parseFloat($.css(element[0], 'paddingTop', true)) || 0) +
			       (parseFloat($.css(element[0], 'paddingBottom', true)) || 0);
		}
		
		function vmargins(element) {
			return (parseFloat($.css(element[0], 'marginTop', true)) || 0) +
			       (parseFloat($.css(element[0], 'marginBottom', true)) || 0);
		}
		
		function vborders(element) {
			return (parseFloat($.css(element[0], 'borderTopWidth', true)) || 0) +
			       (parseFloat($.css(element[0], 'borderBottomWidth', true)) || 0);
		}
		
		/* Misc Utils -----------------------------------------------------------------------------*/
		function noop() { }
		
		function dateCompare(a, b) {
			return a - b;
		}
		
		function arrayMax(a) {
			return Math.max.apply(Math, a);
		}
		
		function zeroPad(n) {
			return (n < 10 ? '0' : '') + n;
		}
		
		function smartProperty(obj, name) { // get a camel-cased/namespaced property of an object
			if (obj[name] !== undefined) {
				return obj[name];
			}
			var parts = name.split(/(?=[A-Z])/),
				i=parts.length-1, res;
			for (; i>=0; i--) {
				res = obj[parts[i].toLowerCase()];
				if (res !== undefined) {
					return res;
				}
			}
			return obj[''];
		}
		
		function htmlEscape(s) {
			return s.replace(/&/g, '&amp;')
				.replace(/</g, '&lt;')
				.replace(/>/g, '&gt;')
				.replace(/'/g, '&#039;')
				.replace(/"/g, '&quot;')
				.replace(/\n/g, '<br />');
		}
		
		function disableTextSelection(element) {
			element
				.attr('unselectable', 'on')
				.css('MozUserSelect', 'none')
				.bind('selectstart.ui', function() { return false; });
		}
		
		function markFirstLast(e) {
			e.children().removeClass('fc-first fc-last').filter(':first-child').addClass('fc-first').end().filter(':last-child').addClass('fc-last');
		}
		
		function setDayID(cell, date) {
			cell.each(function(i, _cell) {
				_cell.className = _cell.className.replace(/^fc-\w*/, 'fc-' + dayIDs[date.getDay()]);
			});
		}
		
		function getSkinCss(event, opt) {
			var source = event.source || {};
			var eventColor = event.color;
			var sourceColor = source.color;
			var optionColor = opt('eventColor');
			var backgroundColor = event.backgroundColor || eventColor || source.backgroundColor || sourceColor || opt('eventBackgroundColor') || optionColor;
			var borderColor = event.borderColor || eventColor || source.borderColor || sourceColor || opt('eventBorderColor') || optionColor;
			var textColor = event.textColor || source.textColor || opt('eventTextColor');
			var statements = [];
			if (backgroundColor) {
				statements.push('background-color:' + backgroundColor);
			}
			if (borderColor) {
				statements.push('border-color:' + borderColor);
			}
			if (textColor) {
				statements.push('color:' + textColor);
			}
			return statements.join(';');
		}
		function applyAll(functions, thisObj, args) {
			if ($.isFunction(functions)) {
				functions = [ functions ];
			}
			if (functions) {
				var i;
				var ret;
				for (i=0; i<functions.length; i++) {
					ret = functions[i].apply(thisObj, args) || ret;
				}
				return ret;
			}
		}
		function firstDefined() {
			for (var i=0; i<arguments.length; i++) {
				if (arguments[i] !== undefined) {
					return arguments[i];
				}
			}
		}
		;;
		fcViews.month = MonthView;
		function MonthView(element, calendar) {
			var t = this;
			t.render = render;
			BasicView.call(t, element, calendar, 'month');
			var opt = t.opt;
			var renderBasic = t.renderBasic;
			var skipHiddenDays = t.skipHiddenDays;
			var getCellsPerWeek = t.getCellsPerWeek;
			var formatDate = calendar.formatDate;
			function render(date, delta) {
				if (delta) {
					addMonths(date, delta);
					date.setDate(1);
				}
				var firstDay = opt('firstDay');
				var start = cloneDate(date, true);
				start.setDate(1);
				var end = addMonths(cloneDate(start), 1);
				var visStart = cloneDate(start);
				addDays(visStart, -((visStart.getDay() - firstDay + 7) % 7));
				skipHiddenDays(visStart);
				var visEnd = cloneDate(end);
				addDays(visEnd, (7 - visEnd.getDay() + firstDay) % 7);
				skipHiddenDays(visEnd, -1, true);
				var colCnt = getCellsPerWeek();
				var rowCnt = Math.round(dayDiff(visEnd, visStart) / 7); // should be no need for Math.round
				if (opt('weekMode') == 'fixed') {
					addDays(visEnd, (6 - rowCnt) * 7); // add weeks to make up for it
					rowCnt = 6;
				}
				t.title = formatDate(start, opt('titleFormat'));
				t.start = start;
				t.end = end;
				t.visStart = visStart;
				t.visEnd = visEnd;
				renderBasic(rowCnt, colCnt, true);
			}
		}
		;;
		fcViews.basicWeek = BasicWeekView;
		function BasicWeekView(element, calendar) {
			var t = this;
			t.render = render;
			BasicView.call(t, element, calendar, 'basicWeek');
			var opt = t.opt;
			var renderBasic = t.renderBasic;
			var skipHiddenDays = t.skipHiddenDays;
			var getCellsPerWeek = t.getCellsPerWeek;
			var formatDates = calendar.formatDates;
			function render(date, delta) {
				if (delta) {
					addDays(date, delta * 7);
				}
				var start = addDays(cloneDate(date), -((date.getDay() - opt('firstDay') + 7) % 7));
				var end = addDays(cloneDate(start), 7);
				var visStart = cloneDate(start);
				skipHiddenDays(visStart);
				var visEnd = cloneDate(end);
				skipHiddenDays(visEnd, -1, true);
				var colCnt = getCellsPerWeek();
				t.start = start;
				t.end = end;
				t.visStart = visStart;
				t.visEnd = visEnd;
				t.title = formatDates(
					visStart,
					addDays(cloneDate(visEnd), -1),
					opt('titleFormat')
				);
				renderBasic(1, colCnt, false);
			}
		};;
		
		fcViews.basicDay = BasicDayView;
		function BasicDayView(element, calendar) {
			var t = this;
			t.render = render;
			BasicView.call(t, element, calendar, 'basicDay');
			var opt = t.opt;
			var renderBasic = t.renderBasic;
			var skipHiddenDays = t.skipHiddenDays;
			var formatDate = calendar.formatDate;
			function render(date, delta) {
				if (delta) {
					addDays(date, delta);
				}
				skipHiddenDays(date, delta < 0 ? -1 : 1);
				var start = cloneDate(date, true);
				var end = addDays(cloneDate(start), 1);
				t.title = formatDate(date, opt('titleFormat'));
				t.start = t.visStart = start;
				t.end = t.visEnd = end;
				renderBasic(1, 1, false);
			}
		};;
		setDefaults({
			weekMode: 'fixed'
		});
		
		var jsonChooseBg = new Array();
		var remindJson = '';//保存json串
		
		function BasicView(element, calendar, viewName) {
			var t = this;
			// exports
			t.renderBasic = renderBasic;
			t.setHeight = setHeight;
			t.setWidth = setWidth;
			t.renderDayOverlay = renderDayOverlay;
			t.defaultSelectionEnd = defaultSelectionEnd;
			t.renderSelection = renderSelection;
			t.clearSelection = clearSelection;
			t.reportDayClick = reportDayClick; // for selection (kinda hacky)
			t.dragStart = dragStart;
			t.dragStop = dragStop;
			t.defaultEventEnd = defaultEventEnd;
			t.getHoverListener = function() { return hoverListener };
			t.colLeft = colLeft;
			t.colRight = colRight;
			t.colContentLeft = colContentLeft;
			t.colContentRight = colContentRight;
			t.getIsCellAllDay = function() { return true };
			t.allDayRow = allDayRow;
			t.getRowCnt = function() { return rowCnt };
			t.getColCnt = function() { return colCnt };
			t.getColWidth = function() { return colWidth };
			t.getDaySegmentContainer = function() { return daySegmentContainer };
			// imports
			View.call(t, element, calendar, viewName);
			OverlayManager.call(t);
			SelectionManager.call(t);
			BasicEventRenderer.call(t);
			var opt = t.opt;
			var trigger = t.trigger;
			var renderOverlay = t.renderOverlay;
			var clearOverlays = t.clearOverlays;
			var daySelectionMousedown = t.daySelectionMousedown;
			var cellToDate = t.cellToDate;
			var dateToCell = t.dateToCell;
			var rangeToSegments = t.rangeToSegments;
			var formatDate = calendar.formatDate;
			// locals
			var table;
			var head;
			var headCells;
			var body;
			var bodyRows;
			var bodyCells;
			var bodyFirstCells;
			var firstRowCellInners;
			var firstRowCellContentInners;
			var daySegmentContainer;
			var viewWidth;
			var viewHeight;
			var colWidth;
			var weekNumberWidth;
			var rowCnt, colCnt;
			var showNumbers;
			var coordinateGrid;
			var hoverListener;
			var colPositions;
			var colContentPositions;
			var tm;
			var colFormat;
			var showWeekNumbers;
			var weekNumberTitle;
			var weekNumberFormat;
			/* Rendering ------------------------------------------------------------*/
			disableTextSelection(element.addClass('fc-grid'));
			function renderBasic(_rowCnt, _colCnt, _showNumbers) {
				rowCnt = _rowCnt;
				colCnt = _colCnt;
				showNumbers = _showNumbers;
				updateOptions();
				if (!body) {
					buildEventContainer();
				}
				buildTable();
			}
			function updateOptions() {
				tm = opt('theme') ? 'ui' : 'fc';
				colFormat = opt('columnFormat');
				showWeekNumbers = opt('weekNumbers');
				weekNumberTitle = opt('weekNumberTitle');
				if (opt('weekNumberCalculation') != 'iso') {
					weekNumberFormat = "w";
				} else {
					weekNumberFormat = "W";
				}
			}
			function buildEventContainer() {
				daySegmentContainer = $("<div class='fc-event-container' style='position:absolute;z-index:8;top:0;left:0'/>").appendTo(element);
			}
			//添加格子
			function buildTable() {
				var html = buildTableHTML();
				if (table) {
					table.remove();
				}
				if(isNull(remindJson)){
					remindJson = event
				}
				if(jsonChooseBg.length == 0){
					var jsonBeans = JSON.parse(remindJson.currentTarget.responseText).rows;
					$.each(jsonBeans, function(i, item){
						if(item.showBg == '2'){
							jsonChooseBg.push(item.start);
							jsonChooseBg = jsonChooseBg.concat(getAll(item.start, item.end));
							jsonChooseBg.push(item.end);
						}
					});
				}
				table = $(html).appendTo(element);
				head = table.find('thead');
				headCells = head.find('.fc-day-header');
				body = table.find('tbody');
				bodyRows = body.find('tr');
				bodyCells = body.find('.fc-day');
				bodyFirstCells = bodyRows.find('td:first-child');
				firstRowCellInners = bodyRows.eq(0).find('.fc-day > div');
				firstRowCellContentInners = bodyRows.eq(0).find('.fc-day-content > div');
				markFirstLast(head.add(head.find('tr'))); // marks first+last tr/th's
				markFirstLast(bodyRows); // marks first+last td's
				bodyRows.eq(0).addClass('fc-first');
				bodyRows.filter(':last').addClass('fc-last');
				bodyCells.each(function(i, _cell) {
					var date = cellToDate(Math.floor(i / colCnt), i % colCnt);
					trigger('dayRender', t, date, $(_cell));
				});
				dayBind(bodyCells);
				bodyCells.each(function(i, _cell) {
					var date = cellToDate(Math.floor(i / colCnt), i % colCnt);
					if($.inArray(date.format('yyyy-MM-dd'), jsonChooseBg) >= 0){
						$(_cell).addClass("holiday-schedule");
						var con = $(_cell).children('div').find(".fc-day-number").html();
						$(_cell).children('div').find(".fc-day-number").html(con + '<div class="xiu">休</div>');
					}
				});
			}
			function buildTableHTML() {
				var html = "<table class='fc-border-separate' style='width:100%' cellspacing='0'>" +
					buildHeadHTML() +
					buildBodyHTML() +
					"</table>";
				return html;
			}
			function buildHeadHTML() {
				var headerClass = tm + "-widget-header";
				var html = '';
				var col;
				var date;
				html += "<thead><tr>";
				if (showWeekNumbers) {
					html += "<th class='fc-week-number " + headerClass + "'>" +
						htmlEscape(weekNumberTitle) +
						"</th>";
				}
				for (col=0; col<colCnt; col++) {
					date = cellToDate(0, col);
					html += "<th class='fc-day-header fc-" + dayIDs[date.getDay()] + " " + headerClass + "'>" +
						htmlEscape(formatDate(date, colFormat)) +
						"</th>";
				}
				html += "</tr></thead>";
				return html;
			}
			function buildBodyHTML() {
				var contentClass = tm + "-widget-content";
				var html = '';
				var row;
				var col;
				var date;
				html += "<tbody>";
				for (row=0; row<rowCnt; row++) {
					html += "<tr class='fc-week'>";
					if (showWeekNumbers) {
						date = cellToDate(row, 0);
						html +=
							"<td class='fc-week-number " + contentClass + "'>" +
							"<div>" +
							htmlEscape(formatDate(date, weekNumberFormat)) +
							"</div>" +
							"</td>";
					}
					for (col=0; col<colCnt; col++) {
						date = cellToDate(row, col);
						html += buildCellHTML(date);
					}
					html += "</tr>";
				}
				html += "</tbody>";
				return html;
			}
			function buildCellHTML(date) {
				var contentClass = tm + "-widget-content";
				var month = t.start.getMonth();
				var today = clearTime(new Date());
				var html = '';
				var classNames = [
					'fc-day',
					'fc-' + dayIDs[date.getDay()],
					contentClass
				];
				if (date.getMonth() != month) {
					classNames.push('fc-other-month');
				}
				if (+date == +today) {
					classNames.push(
						'fc-today',
						tm + '-state-highlight'
					);
				} else if (date < today) {
					classNames.push('fc-past');
				} else {
					classNames.push('fc-future');
				}
				html += "<td" +
					" class='" + classNames.join(' ') + "'" +
					" data-date='" + formatDate(date, 'yyyy-MM-dd') + "'" +
					">" +
					"<div>";
				if (showNumbers) {
					var cnMonth = CnMonthofDate(date);//农历月 
				    var cnDay = CnDayofDate(date);//农历日 
				    var solar = SolarTerm(date);//农历节气 
				    var cnMonDay;
				    if(solar != ''){
				    	cnDay = solar; 
				    	cnMonDay = '<font style="color:red">' + cnDay + '</font>'; 
				    }else{
				    	cnMonDay = cnMonth + cnDay; 
				    }
				    var holiday = ''; 
				    if(cnDay == '正月') 
				        holiday = '春节'; 
				    switch(cnMonDay){ 
				        case '正月初一': holiday = '春节';break; 
				        case '正月十五': holiday = '元宵';break; 
				        case '五月初五': holiday = '端午';break; 
				        case '八月十五': holiday = '中秋';break; 
				        case '九月初九': holiday = '重阳';break; 
				        case '腊月三十': holiday = '除夕';break; 
				    } 
				    if(holiday != ''){
				    	cnMonDay = '<font style="color:red">' + holiday + '</font>';
				    }
				    //加载背景颜色
					html += "<div class='fc-day-number'>" + date.getDate() + "<br><span class='solarday'>" + cnMonDay + "</span></div>";//新版本
				}
				html += "<div class='fc-day-content'>" +
								"<div style='position:relative'>&nbsp;</div>" +
								"</div>" +
							"</div>" +
						"</td>";
				return html;
			}
			function setHeight(height) {
				viewHeight = height;
				
				var bodyHeight = viewHeight - head.height();
				var rowHeight;
				var rowHeightLast;
				var cell;
					
				if (opt('weekMode') == 'variable') {
					rowHeight = rowHeightLast = Math.floor(bodyHeight / (rowCnt==1 ? 2 : 6));
				}else{
					rowHeight = Math.floor(bodyHeight / rowCnt);
					rowHeightLast = bodyHeight - rowHeight * (rowCnt-1);
				}
				
				bodyFirstCells.each(function(i, _cell) {
					if (i < rowCnt) {
						cell = $(_cell);
						cell.find('> div').css(
							'height',
							(i==rowCnt-1 ? rowHeightLast : rowHeight) - vsides(cell)
						);
					}
				});
				
			}
			function setWidth(width) {
				viewWidth = width;
				colPositions.clear();
				colContentPositions.clear();
		
				weekNumberWidth = 0;
				if (showWeekNumbers) {
					weekNumberWidth = head.find('th.fc-week-number').outerWidth();
				}
				colWidth = Math.floor((viewWidth - weekNumberWidth) / colCnt);
				setOuterWidth(headCells.slice(0, -1), colWidth);
			}
			function dayBind(days) {
				days.click(dayClick).mousedown(daySelectionMousedown);
			}
			function dayClick(ev) {
				if (!opt('selectable')) { // if selectable, SelectionManager will worry about dayClick
					var date = parseISO8601($(this).data('date'));
					trigger('dayClick', this, date, true, ev);
				}
			}
			function renderDayOverlay(overlayStart, overlayEnd, refreshCoordinateGrid) { // overlayEnd is exclusive
				if (refreshCoordinateGrid) {
					coordinateGrid.build();
				}
				var segments = rangeToSegments(overlayStart, overlayEnd);
				for (var i=0; i<segments.length; i++) {
					var segment = segments[i];
					dayBind(
						renderCellOverlay(
							segment.row,
							segment.leftCol,
							segment.row,
							segment.rightCol
						)
					);//添加天的日程
				}
			}
			function renderCellOverlay(row0, col0, row1, col1) { // row1,col1 is inclusive
				var rect = coordinateGrid.rect(row0, col0, row1, col1, element);
				return renderOverlay(rect, element);
			}
			function defaultSelectionEnd(startDate, allDay) {
				return cloneDate(startDate);
			}
			function renderSelection(startDate, endDate, allDay) {
				renderDayOverlay(startDate, addDays(cloneDate(endDate), 1), true); // rebuild every time???
			}
			function clearSelection() {
				clearOverlays();
			}
			function reportDayClick(date, allDay, ev) {
				var cell = dateToCell(date);
				var _element = bodyCells[cell.row*colCnt + cell.col];
				trigger('dayClick', _element, date, allDay, ev);
			}
			function dragStart(_dragElement, ev, ui) {
				hoverListener.start(function(cell) {
					clearOverlays();
					if (cell) {
						renderCellOverlay(cell.row, cell.col, cell.row, cell.col);
					}
				}, ev);
			}
			function dragStop(_dragElement, ev, ui) {
				var cell = hoverListener.stop();
				clearOverlays();
				if (cell) {
					var d = cellToDate(cell);
					trigger('drop', _dragElement, d, true, ev, ui);
				}
			}
			function defaultEventEnd(event) {
				return cloneDate(event.start);
			}
			coordinateGrid = new CoordinateGrid(function(rows, cols) {
				var e, n, p;
				headCells.each(function(i, _e) {
					e = $(_e);
					n = e.offset().left;
					if (i) {
						p[1] = n;
					}
					p = [n];
					cols[i] = p;
				});
				p[1] = n + e.outerWidth();
				bodyRows.each(function(i, _e) {
					if (i < rowCnt) {
						e = $(_e);
						n = e.offset().top;
						if (i) {
							p[1] = n;
						}
						p = [n];
						rows[i] = p;
					}
				});
				p[1] = n + e.outerHeight();
			});
			hoverListener = new HoverListener(coordinateGrid);
			colPositions = new HorizontalPositionCache(function(col) {
				return firstRowCellInners.eq(col);
			});
			colContentPositions = new HorizontalPositionCache(function(col) {
				return firstRowCellContentInners.eq(col);
			});
			function colLeft(col) {
				return colPositions.left(col);
			}
			function colRight(col) {
				return colPositions.right(col);
			}
			function colContentLeft(col) {
				return colContentPositions.left(col);
			}
			function colContentRight(col) {
				return colContentPositions.right(col);
			}
			function allDayRow(i) {
				return bodyRows.eq(i);
			}
		}
		;;
		function BasicEventRenderer() {
			var t = this;
			t.renderEvents = renderEvents;
			t.clearEvents = clearEvents;
			DayEventRenderer.call(t);
			function renderEvents(events, modifiedEventId) {
				t.renderDayEvents(events, modifiedEventId);
			}
			function clearEvents() {
				t.getDaySegmentContainer().empty();
			}
		}
		;;
		fcViews.agendaWeek = AgendaWeekView;
		function AgendaWeekView(element, calendar) {
			var t = this;
			t.render = render;
			AgendaView.call(t, element, calendar, 'agendaWeek');
			var opt = t.opt;
			var renderAgenda = t.renderAgenda;
			var skipHiddenDays = t.skipHiddenDays;
			var getCellsPerWeek = t.getCellsPerWeek;
			var formatDates = calendar.formatDates;
			function render(date, delta) {
				if (delta) {
					addDays(date, delta * 7);
				}
				var start = addDays(cloneDate(date), -((date.getDay() - opt('firstDay') + 7) % 7));
				var end = addDays(cloneDate(start), 7);
				var visStart = cloneDate(start);
				skipHiddenDays(visStart);
				var visEnd = cloneDate(end);
				skipHiddenDays(visEnd, -1, true);
				var colCnt = getCellsPerWeek();
				t.title = formatDates(
					visStart,
					addDays(cloneDate(visEnd), -1),
					opt('titleFormat')
				);
				t.start = start;
				t.end = end;
				t.visStart = visStart;
				t.visEnd = visEnd;
				renderAgenda(colCnt);
			}
		}
		;;
		fcViews.agendaDay = AgendaDayView;
		function AgendaDayView(element, calendar) {
			var t = this;
			t.render = render;
			AgendaView.call(t, element, calendar, 'agendaDay');
			var opt = t.opt;
			var renderAgenda = t.renderAgenda;
			var skipHiddenDays = t.skipHiddenDays;
			var formatDate = calendar.formatDate;
			function render(date, delta) {
				if (delta) {
					addDays(date, delta);
				}
				skipHiddenDays(date, delta < 0 ? -1 : 1);
				var start = cloneDate(date, true);
				var end = addDays(cloneDate(start), 1);
				t.title = formatDate(date, opt('titleFormat'));
				t.start = t.visStart = start;
				t.end = t.visEnd = end;
				renderAgenda(1);
			}
		};;
		setDefaults({
			allDaySlot: true,
			allDayText: '全天',
			firstHour: 6,//滚动条滚动到得起始时间
			slotMinutes: 30,//每行的时间间隔
			defaultEventMinutes: 120,
			axisFormat: 'tt HH:mm',
			timeFormat: {
				agenda: 'tt HH:mm{ ~ tt HH:mm}'
			},
			dragOpacity: {
				agenda: .5
			},
			minTime: 0,
			maxTime: 24,//每天显示到几点结束
			slotEventOverlap: true//设置议程视图中的事件是否可以重叠
		});
		function AgendaView(element, calendar, viewName) {
			var t = this;
			// exports
			t.renderAgenda = renderAgenda;
			t.setWidth = setWidth;
			t.setHeight = setHeight;
			t.afterRender = afterRender;
			t.defaultEventEnd = defaultEventEnd;
			t.timePosition = timePosition;
			t.getIsCellAllDay = getIsCellAllDay;
			t.allDayRow = getAllDayRow;
			t.getCoordinateGrid = function() { return coordinateGrid }; // specifically for AgendaEventRenderer
			t.getHoverListener = function() { return hoverListener };
			t.colLeft = colLeft;
			t.colRight = colRight;
			t.colContentLeft = colContentLeft;
			t.colContentRight = colContentRight;
			t.getDaySegmentContainer = function() { return daySegmentContainer };
			t.getSlotSegmentContainer = function() { return slotSegmentContainer };
			t.getMinMinute = function() { return minMinute };
			t.getMaxMinute = function() { return maxMinute };
			t.getSlotContainer = function() { return slotContainer };
			t.getRowCnt = function() { return 1 };
			t.getColCnt = function() { return colCnt };
			t.getColWidth = function() { return colWidth };
			t.getSnapHeight = function() { return snapHeight };
			t.getSnapMinutes = function() { return snapMinutes };
			t.defaultSelectionEnd = defaultSelectionEnd;
			t.renderDayOverlay = renderDayOverlay;
			t.renderSelection = renderSelection;
			t.clearSelection = clearSelection;
			t.reportDayClick = reportDayClick; // selection mousedown hack
			t.dragStart = dragStart;
			t.dragStop = dragStop;
			View.call(t, element, calendar, viewName);
			OverlayManager.call(t);
			SelectionManager.call(t);
			AgendaEventRenderer.call(t);
			var opt = t.opt;
			var trigger = t.trigger;
			var renderOverlay = t.renderOverlay;
			var clearOverlays = t.clearOverlays;
			var reportSelection = t.reportSelection;
			var unselect = t.unselect;
			var daySelectionMousedown = t.daySelectionMousedown;
			var slotSegHtml = t.slotSegHtml;
			var cellToDate = t.cellToDate;
			var dateToCell = t.dateToCell;
			var rangeToSegments = t.rangeToSegments;
			var formatDate = calendar.formatDate;
			var dayTable;
			var dayHead;
			var dayHeadCells;
			var dayBody;
			var dayBodyCells;
			var dayBodyCellInners;
			var dayBodyCellContentInners;
			var dayBodyFirstCell;
			var dayBodyFirstCellStretcher;
			var slotLayer;
			var daySegmentContainer;
			var allDayTable;
			var allDayRow;
			var slotScroller;
			var slotContainer;
			var slotSegmentContainer;
			var slotTable;
			var selectionHelper;
			var viewWidth;
			var viewHeight;
			var axisWidth;
			var colWidth;
			var gutterWidth;
			var slotHeight; //what if slotHeight changes? (see issue 650)
			var snapMinutes;
			var snapRatio; // ratio of number of "selection" slots to normal slots. (ex: 1, 2, 4)
			var snapHeight; // holds the pixel hight of a "selection" slot
			var colCnt;
			var slotCnt;
			var coordinateGrid;
			var hoverListener;
			var colPositions;
			var colContentPositions;
			var slotTopCache = {};
			var tm;
			var rtl;
			var minMinute, maxMinute;
			var colFormat;
			var showWeekNumbers;
			var weekNumberTitle;
			var weekNumberFormat;
			/* Rendering -----------------------------------------------------------------------------*/
			disableTextSelection(element.addClass('fc-agenda'));
			function renderAgenda(c) {
				colCnt = c;
				updateOptions();
				if (!dayTable) { // first time rendering?
					buildSkeleton(); // builds day table, slot area, events containers
				} else {
					buildDayTable(); // rebuilds day table
				}
			}
			function updateOptions() {
				tm = opt('theme') ? 'ui' : 'fc';
				rtl = opt('isRTL')
				minMinute = parseTime(opt('minTime'));
				maxMinute = parseTime(opt('maxTime'));
				colFormat = opt('columnFormat');
				showWeekNumbers = opt('weekNumbers');
				weekNumberTitle = opt('weekNumberTitle');
				if (opt('weekNumberCalculation') != 'iso') {
					weekNumberFormat = "w";
				} else {
					weekNumberFormat = "W";
				}
				snapMinutes = opt('snapMinutes') || opt('slotMinutes');
			}
			/* Build DOM -----------------------------------------------------------------------*/
			function buildSkeleton() {
				var headerClass = tm + "-widget-header";
				var contentClass = tm + "-widget-content";
				var s;
				var d;
				var i;
				var maxd;
				var minutes;
				var slotNormal = opt('slotMinutes') % 15 == 0;
				buildDayTable();
				slotLayer = $("<div style='position:absolute;z-index:2;left:0;width:100%'/>").appendTo(element);
				if (opt('allDaySlot')) {
					daySegmentContainer = $("<div class='fc-event-container' style='position:absolute;z-index:8;top:0;left:0'/>").appendTo(slotLayer);
					s = "<table style='width:100%' class='fc-agenda-allday' cellspacing='0'>" +
						"<tr>" +
						"<th class='" + headerClass + " fc-agenda-axis'>" + opt('allDayText') + "</th>" +
						"<td>" +
						"<div class='fc-day-content'><div style='position:relative'/></div>" +
						"</td>" +
						"<th class='" + headerClass + " fc-agenda-gutter'>&nbsp;</th>" +
						"</tr>" +
						"</table>";
					allDayTable = $(s).appendTo(slotLayer);
					allDayRow = allDayTable.find('tr');
					dayBind(allDayRow.find('td'));
					slotLayer.append( "<div class='fc-agenda-divider " + headerClass + "'><div class='fc-agenda-divider-inner'/></div>"
					);
				}else{
					daySegmentContainer = $([]); // in jQuery 1.4, we can just do $()
				}
				slotScroller = $("<div style='position:absolute;width:100%;overflow-x:hidden;overflow-y:auto'/>") .appendTo(slotLayer);
				slotContainer = $("<div style='position:relative;width:100%;overflow:hidden'/>").appendTo(slotScroller);
				slotSegmentContainer = $("<div class='fc-event-container' style='position:absolute;z-index:8;top:0;left:0'/>").appendTo(slotContainer);
				s = "<table class='fc-agenda-slots' style='width:100%' cellspacing='0'>" + "<tbody>";
				d = zeroDate();
				maxd = addMinutes(cloneDate(d), maxMinute);
				addMinutes(d, minMinute);
				slotCnt = 0;
				for (i=0; d < maxd; i++) {
					minutes = d.getMinutes();
					s +=
						"<tr class='fc-slot" + i + ' ' + (!minutes ? '' : 'fc-minor') + "'>" +
						"<th class='fc-agenda-axis " + headerClass + "'>" +
						((!slotNormal || !minutes) ? formatDate(d, opt('axisFormat')) : '&nbsp;') +
						"</th>" +
						"<td class='" + contentClass + "'>" +
						"<div style='position:relative'>&nbsp;</div>" +
						"</td>" +
						"</tr>";
					addMinutes(d, opt('slotMinutes'));
					slotCnt++;
				}
				s += "</tbody></table>";
				slotTable = $(s).appendTo(slotContainer);
				slotBind(slotTable.find('td'));
			}
			/* Build Day Table -----------------------------------------------------------------------*/
			function buildDayTable() {
				var html = buildDayTableHTML();
				if (dayTable) {
					dayTable.remove();
				}
				dayTable = $(html).appendTo(element);
				dayHead = dayTable.find('thead');
				dayHeadCells = dayHead.find('th').slice(1, -1); // exclude gutter
				dayBody = dayTable.find('tbody');
				dayBodyCells = dayBody.find('td').slice(0, -1); // exclude gutter
				dayBodyCellInners = dayBodyCells.find('> div');
				dayBodyCellContentInners = dayBodyCells.find('.fc-day-content > div');
				dayBodyFirstCell = dayBodyCells.eq(0);
				dayBodyFirstCellStretcher = dayBodyCellInners.eq(0);
				markFirstLast(dayHead.add(dayHead.find('tr')));
				markFirstLast(dayBody.add(dayBody.find('tr')));
			}
		
			function buildDayTableHTML() {
				var html = "<table style='width:100%' class='fc-agenda-days fc-border-separate' cellspacing='0'>" +
					buildDayTableHeadHTML() +
					buildDayTableBodyHTML() +
					"</table>";
				return html;
			}
			function buildDayTableHeadHTML() {
				var headerClass = tm + "-widget-header";
				var date;
				var html = '';
				var weekText;
				var col;
				html += "<thead><tr>";
				if (showWeekNumbers) {
					date = cellToDate(0, 0);
					weekText = formatDate(date, weekNumberFormat);
					if (rtl) {
						weekText += weekNumberTitle;
					} else {
						weekText = weekNumberTitle + weekText;
					}
					html +=
						"<th class='fc-agenda-axis fc-week-number " + headerClass + "'>" +
						htmlEscape(weekText) +
						"</th>";
				} else {
					html += "<th class='fc-agenda-axis " + headerClass + "'>&nbsp;</th>";
				}
				for (col=0; col<colCnt; col++) {
					date = cellToDate(0, col);
					html +=
						"<th class='fc-" + dayIDs[date.getDay()] + " fc-col" + col + ' ' + headerClass + "'>" +
						htmlEscape(formatDate(date, colFormat)) +
						"</th>";
				}
				html += "<th class='fc-agenda-gutter " + headerClass + "'>&nbsp;</th>" +
					"</tr>" +
					"</thead>";
				return html;
			}
			function buildDayTableBodyHTML() {
				var headerClass = tm + "-widget-header"; // TODO: make these when updateOptions() called
				var contentClass = tm + "-widget-content";
				var date;
				var today = clearTime(new Date());
				var col;
				var cellsHTML;
				var cellHTML;
				var classNames;
				var html = '';
				html += "<tbody>" +
					"<tr>" +
					"<th class='fc-agenda-axis " + headerClass + "'>&nbsp;</th>";
				cellsHTML = '';
				for (col=0; col<colCnt; col++) {
					date = cellToDate(0, col);
					classNames = [
						'fc-col' + col,
						'fc-' + dayIDs[date.getDay()],
						contentClass
					];
					if (+date == +today) {
						classNames.push(
							tm + '-state-highlight',
							'fc-today'
						);
					}
					else if (date < today) {
						classNames.push('fc-past');
					}
					else {
						classNames.push('fc-future');
					}
					cellHTML = "<td class='" + classNames.join(' ') + "'>" +
						"<div>" +
						"<div class='fc-day-content'>" +
						"<div style='position:relative'>&nbsp;</div>" +
						"</div>" +
						"</div>" +
						"</td>";
					cellsHTML += cellHTML;
				}
				html += cellsHTML;
				html +=
					"<td class='fc-agenda-gutter " + contentClass + "'>&nbsp;</td>" +
					"</tr>" +
					"</tbody>";
				return html;
			}
			/* Dimensions -----------------------------------------------------------------------*/
			function setHeight(height) {
				if (height === undefined) {
					height = viewHeight;
				}
				viewHeight = height;
				slotTopCache = {};
				var headHeight = dayBody.position().top;
				var allDayHeight = slotScroller.position().top; // including divider
				var bodyHeight = Math.min( // total body height, including borders
					height - headHeight,   // when scrollbars
					slotTable.height() + allDayHeight + 1 // when no scrollbars. +1 for bottom border
				);
				dayBodyFirstCellStretcher.height(bodyHeight - vsides(dayBodyFirstCell));
				slotLayer.css('top', headHeight);
				slotScroller.height(bodyHeight - allDayHeight - 1);
				slotHeight = slotTable.find('tr:first').height() + 1; // +1 for bottom border
				snapRatio = opt('slotMinutes') / snapMinutes;
				snapHeight = slotHeight / snapRatio;
			}
			function setWidth(width) {
				viewWidth = width;
				colPositions.clear();
				colContentPositions.clear();
				var axisFirstCells = dayHead.find('th:first');
				if (allDayTable) {
					axisFirstCells = axisFirstCells.add(allDayTable.find('th:first'));
				}
				axisFirstCells = axisFirstCells.add(slotTable.find('th:first'));
				axisWidth = 0;
				setOuterWidth(axisFirstCells.width('').each(function(i, _cell) {
							axisWidth = Math.max(axisWidth, $(_cell).outerWidth());
						}), axisWidth);
				var gutterCells = dayTable.find('.fc-agenda-gutter');
				if (allDayTable) {
					gutterCells = gutterCells.add(allDayTable.find('th.fc-agenda-gutter'));
				}
				var slotTableWidth = slotScroller[0].clientWidth; // needs to be done after axisWidth (for IE7)
				gutterWidth = slotScroller.width() - slotTableWidth;
				if (gutterWidth) {
					setOuterWidth(gutterCells, gutterWidth);
					gutterCells.show().prev().removeClass('fc-last');
				}else{
					gutterCells.hide().prev().addClass('fc-last');
				}
				colWidth = Math.floor((slotTableWidth - axisWidth) / colCnt);
				setOuterWidth(dayHeadCells.slice(0, -1), colWidth);
			}
			/* Scrolling -----------------------------------------------------------------------*/
			function resetScroll() {
				var d0 = zeroDate();
				var scrollDate = cloneDate(d0);
				scrollDate.setHours(opt('firstHour'));
				var top = timePosition(d0, scrollDate) + 1; // +1 for the border
				function scroll() {
					slotScroller.scrollTop(top);
				}
				scroll();
				setTimeout(scroll, 0); // overrides any previous scroll state made by the browser
			}
			function afterRender() { // after the view has been freshly rendered and sized
				resetScroll();
			}
			function dayBind(cells) {
				cells.click(slotClick).mousedown(daySelectionMousedown);
			}
			function slotBind(cells) {
				cells.click(slotClick).mousedown(slotSelectionMousedown);
			}
			function slotClick(ev) {
				if (!opt('selectable')) { // if selectable, SelectionManager will worry about dayClick
					var col = Math.min(colCnt-1, Math.floor((ev.pageX - dayTable.offset().left - axisWidth) / colWidth));
					var date = cellToDate(0, col);
					var rowMatch = this.parentNode.className.match(/fc-slot(\d+)/); // TODO: maybe use data
					if (rowMatch) {
						var mins = parseInt(rowMatch[1]) * opt('slotMinutes');
						var hours = Math.floor(mins/60);
						date.setHours(hours);
						date.setMinutes(mins%60 + minMinute);
						trigger('dayClick', dayBodyCells[col], date, false, ev);
					}else{
						trigger('dayClick', dayBodyCells[col], date, true, ev);
					}
				}
			}
			function renderDayOverlay(overlayStart, overlayEnd, refreshCoordinateGrid) { // overlayEnd is exclusive
				if (refreshCoordinateGrid) {
					coordinateGrid.build();
				}
				var segments = rangeToSegments(overlayStart, overlayEnd);
				for (var i=0; i<segments.length; i++) {
					var segment = segments[i];
					dayBind(
						renderCellOverlay(
							segment.row,
							segment.leftCol,
							segment.row,
							segment.rightCol
						)
					);
				}
			}
			
			
			function renderCellOverlay(row0, col0, row1, col1) { // only for all-day?
				var rect = coordinateGrid.rect(row0, col0, row1, col1, slotLayer);
				return renderOverlay(rect, slotLayer);
			}
			
		
			function renderSlotOverlay(overlayStart, overlayEnd) {
				for (var i=0; i<colCnt; i++) {
					var dayStart = cellToDate(0, i);
					var dayEnd = addDays(cloneDate(dayStart), 1);
					var stretchStart = new Date(Math.max(dayStart, overlayStart));
					var stretchEnd = new Date(Math.min(dayEnd, overlayEnd));
					if (stretchStart < stretchEnd) {
						var rect = coordinateGrid.rect(0, i, 0, i, slotContainer); // only use it for horizontal coords
						var top = timePosition(dayStart, stretchStart);
						var bottom = timePosition(dayStart, stretchEnd);
						rect.top = top;
						rect.height = bottom - top;
						slotBind(
							renderOverlay(rect, slotContainer)
						);
					}
				}
			}
			
			
			
			/* Coordinate Utilities
			-----------------------------------------------------------------------------*/
			
			
			coordinateGrid = new CoordinateGrid(function(rows, cols) {
				var e, n, p;
				dayHeadCells.each(function(i, _e) {
					e = $(_e);
					n = e.offset().left;
					if (i) {
						p[1] = n;
					}
					p = [n];
					cols[i] = p;
				});
				p[1] = n + e.outerWidth();
				if (opt('allDaySlot')) {
					e = allDayRow;
					n = e.offset().top;
					rows[0] = [n, n+e.outerHeight()];
				}
				var slotTableTop = slotContainer.offset().top;
				var slotScrollerTop = slotScroller.offset().top;
				var slotScrollerBottom = slotScrollerTop + slotScroller.outerHeight();
				function constrain(n) {
					return Math.max(slotScrollerTop, Math.min(slotScrollerBottom, n));
				}
				for (var i=0; i<slotCnt*snapRatio; i++) { // adapt slot count to increased/decreased selection slot count
					rows.push([
						constrain(slotTableTop + snapHeight*i),
						constrain(slotTableTop + snapHeight*(i+1))
					]);
				}
			});
			
			
			hoverListener = new HoverListener(coordinateGrid);
			
			colPositions = new HorizontalPositionCache(function(col) {
				return dayBodyCellInners.eq(col);
			});
			
			colContentPositions = new HorizontalPositionCache(function(col) {
				return dayBodyCellContentInners.eq(col);
			});
			
			
			function colLeft(col) {
				return colPositions.left(col);
			}
		
		
			function colContentLeft(col) {
				return colContentPositions.left(col);
			}
		
		
			function colRight(col) {
				return colPositions.right(col);
			}
			
			
			function colContentRight(col) {
				return colContentPositions.right(col);
			}
		
		
			function getIsCellAllDay(cell) {
				return opt('allDaySlot') && !cell.row;
			}
		
		
			function realCellToDate(cell) { // ugh "real" ... but blame it on our abuse of the "cell" system
				var d = cellToDate(0, cell.col);
				var slotIndex = cell.row;
				if (opt('allDaySlot')) {
					slotIndex--;
				}
				if (slotIndex >= 0) {
					addMinutes(d, minMinute + slotIndex * snapMinutes);
				}
				return d;
			}
			
			
			// get the Y coordinate of the given time on the given day (both Date objects)
			function timePosition(day, time) { // both date objects. day holds 00:00 of current day
				day = cloneDate(day, true);
				if (time < addMinutes(cloneDate(day), minMinute)) {
					return 0;
				}
				if (time >= addMinutes(cloneDate(day), maxMinute)) {
					return slotTable.height();
				}
				var slotMinutes = opt('slotMinutes'),
					minutes = time.getHours()*60 + time.getMinutes() - minMinute,
					slotI = Math.floor(minutes / slotMinutes),
					slotTop = slotTopCache[slotI];
				if (slotTop === undefined) {
					slotTop = slotTopCache[slotI] =
						slotTable.find('tr').eq(slotI).find('td div')[0].offsetTop;
						// .eq() is faster than ":eq()" selector
						// [0].offsetTop is faster than .position().top (do we really need this optimization?)
						// a better optimization would be to cache all these divs
				}
				return Math.max(0, Math.round(
					slotTop - 1 + slotHeight * ((minutes % slotMinutes) / slotMinutes)
				));
			}
			
			
			function getAllDayRow(index) {
				return allDayRow;
			}
			
			
			function defaultEventEnd(event) {
				var start = cloneDate(event.start);
				if (event.allDay) {
					return start;
				}
				return addMinutes(start, opt('defaultEventMinutes'));
			}
			
			
			
			/* Selection
			---------------------------------------------------------------------------------*/
			
			
			function defaultSelectionEnd(startDate, allDay) {
				if (allDay) {
					return cloneDate(startDate);
				}
				return addMinutes(cloneDate(startDate), opt('slotMinutes'));
			}
			
			
			function renderSelection(startDate, endDate, allDay) { // only for all-day
				if (allDay) {
					if (opt('allDaySlot')) {
						renderDayOverlay(startDate, addDays(cloneDate(endDate), 1), true);
					}
				}else{
					renderSlotSelection(startDate, endDate);
				}
			}
			
			
			function renderSlotSelection(startDate, endDate) {
				var helperOption = opt('selectHelper');
				coordinateGrid.build();
				if (helperOption) {
					var col = dateToCell(startDate).col;
					if (col >= 0 && col < colCnt) { // only works when times are on same day
						var rect = coordinateGrid.rect(0, col, 0, col, slotContainer); // only for horizontal coords
						var top = timePosition(startDate, startDate);
						var bottom = timePosition(startDate, endDate);
						if (bottom > top) { // protect against selections that are entirely before or after visible range
							rect.top = top;
							rect.height = bottom - top;
							rect.left += 2;
							rect.width -= 5;
							if ($.isFunction(helperOption)) {
								var helperRes = helperOption(startDate, endDate);
								if (helperRes) {
									rect.position = 'absolute';
									selectionHelper = $(helperRes)
										.css(rect)
										.appendTo(slotContainer);
								}
							}else{
								rect.isStart = true; // conside rect a "seg" now
								rect.isEnd = true;   //
								selectionHelper = $(slotSegHtml(
									{
										title: '',
										start: startDate,
										end: endDate,
										className: ['fc-select-helper'],
										editable: false
									},
									rect
								));
								selectionHelper.css('opacity', opt('dragOpacity'));
							}
							if (selectionHelper) {
								slotBind(selectionHelper);
								slotContainer.append(selectionHelper);
								setOuterWidth(selectionHelper, rect.width, true); // needs to be after appended
								setOuterHeight(selectionHelper, rect.height, true);
							}
						}
					}
				}else{
					renderSlotOverlay(startDate, endDate);
				}
			}
			
			
			function clearSelection() {
				clearOverlays();
				if (selectionHelper) {
					selectionHelper.remove();
					selectionHelper = null;
				}
			}
			
			
			function slotSelectionMousedown(ev) {
				if (ev.which == 1 && opt('selectable')) { // ev.which==1 means left mouse button
					unselect(ev);
					var dates;
					hoverListener.start(function(cell, origCell) {
						clearSelection();
						if (cell && cell.col == origCell.col && !getIsCellAllDay(cell)) {
							var d1 = realCellToDate(origCell);
							var d2 = realCellToDate(cell);
							dates = [
								d1,
								addMinutes(cloneDate(d1), snapMinutes), // calculate minutes depending on selection slot minutes 
								d2,
								addMinutes(cloneDate(d2), snapMinutes)
							].sort(dateCompare);
							renderSlotSelection(dates[0], dates[3]);
						}else{
							dates = null;
						}
					}, ev);
					$(document).one('mouseup', function(ev) {
						hoverListener.stop();
						if (dates) {
							if (+dates[0] == +dates[1]) {
								reportDayClick(dates[0], false, ev);
							}
							reportSelection(dates[0], dates[3], false, ev);
						}
					});
				}
			}
		
		
			function reportDayClick(date, allDay, ev) {
				trigger('dayClick', dayBodyCells[dateToCell(date).col], date, allDay, ev);
			}
			
			
			
			/* External Dragging
			--------------------------------------------------------------------------------*/
			
			
			function dragStart(_dragElement, ev, ui) {
				hoverListener.start(function(cell) {
					clearOverlays();
					if (cell) {
						if (getIsCellAllDay(cell)) {
							renderCellOverlay(cell.row, cell.col, cell.row, cell.col);
						}else{
							var d1 = realCellToDate(cell);
							var d2 = addMinutes(cloneDate(d1), opt('defaultEventMinutes'));
							renderSlotOverlay(d1, d2);
						}
					}
				}, ev);
			}
			function dragStop(_dragElement, ev, ui) {
				var cell = hoverListener.stop();
				clearOverlays();
				if (cell) {
					trigger('drop', _dragElement, realCellToDate(cell), getIsCellAllDay(cell), ev, ui);
				}
			}
		}
		;;
		function AgendaEventRenderer() {
			var t = this;
			t.renderEvents = renderEvents;
			t.clearEvents = clearEvents;
			t.slotSegHtml = slotSegHtml;
			DayEventRenderer.call(t);
			var opt = t.opt;
			var trigger = t.trigger;
			var isEventDraggable = t.isEventDraggable;
			var isEventResizable = t.isEventResizable;
			var eventEnd = t.eventEnd;
			var eventElementHandlers = t.eventElementHandlers;
			var setHeight = t.setHeight;
			var getDaySegmentContainer = t.getDaySegmentContainer;
			var getSlotSegmentContainer = t.getSlotSegmentContainer;
			var getHoverListener = t.getHoverListener;
			var getMaxMinute = t.getMaxMinute;
			var getMinMinute = t.getMinMinute;
			var timePosition = t.timePosition;
			var getIsCellAllDay = t.getIsCellAllDay;
			var colContentLeft = t.colContentLeft;
			var colContentRight = t.colContentRight;
			var cellToDate = t.cellToDate;
			var getColCnt = t.getColCnt;
			var getColWidth = t.getColWidth;
			var getSnapHeight = t.getSnapHeight;
			var getSnapMinutes = t.getSnapMinutes;
			var getSlotContainer = t.getSlotContainer;
			var reportEventElement = t.reportEventElement;
			var showEvents = t.showEvents;
			var hideEvents = t.hideEvents;
			var eventDrop = t.eventDrop;
			var eventResize = t.eventResize;
			var renderDayOverlay = t.renderDayOverlay;
			var clearOverlays = t.clearOverlays;
			var renderDayEvents = t.renderDayEvents;
			var calendar = t.calendar;
			var formatDate = calendar.formatDate;
			var formatDates = calendar.formatDates;
			t.draggableDayEvent = draggableDayEvent;
			/* Rendering ----------------------------------------------------------------------------*/
			function renderEvents(events, modifiedEventId) {
				var i, len=events.length,
					dayEvents=[],
					slotEvents=[];
				for (i=0; i<len; i++) {
					if (events[i].allDay) {
						dayEvents.push(events[i]);
					}else{
						slotEvents.push(events[i]);
					}
				}
				if (opt('allDaySlot')) {
					renderDayEvents(dayEvents, modifiedEventId);
					setHeight(); // no params means set to viewHeight
				}
				renderSlotSegs(compileSlotSegs(slotEvents), modifiedEventId);
			}
			function clearEvents() {
				getDaySegmentContainer().empty();
				getSlotSegmentContainer().empty();
			}
			function compileSlotSegs(events) {
				var colCnt = getColCnt(),
					minMinute = getMinMinute(),
					maxMinute = getMaxMinute(),
					d,
					visEventEnds = $.map(events, slotEventEnd),
					i,
					j, seg,
					colSegs,
					segs = [];
				for (i=0; i<colCnt; i++) {
					d = cellToDate(0, i);
					addMinutes(d, minMinute);
					colSegs = sliceSegs(
						events,
						visEventEnds,
						d,
						addMinutes(cloneDate(d), maxMinute-minMinute)
					);
					colSegs = placeSlotSegs(colSegs); // returns a new order
					for (j=0; j<colSegs.length; j++) {
						seg = colSegs[j];
						seg.col = i;
						segs.push(seg);
					}
				}
				return segs;
			}
			function sliceSegs(events, visEventEnds, start, end) {
				var segs = [],
					i, len=events.length, event,
					eventStart, eventEnd,
					segStart, segEnd,
					isStart, isEnd;
				for (i=0; i<len; i++) {
					event = events[i];
					eventStart = event.start;
					eventEnd = visEventEnds[i];
					if (eventEnd > start && eventStart < end) {
						if (eventStart < start) {
							segStart = cloneDate(start);
							isStart = false;
						}else{
							segStart = eventStart;
							isStart = true;
						}
						if (eventEnd > end) {
							segEnd = cloneDate(end);
							isEnd = false;
						}else{
							segEnd = eventEnd;
							isEnd = true;
						}
						segs.push({
							event: event,
							start: segStart,
							end: segEnd,
							isStart: isStart,
							isEnd: isEnd
						});
					}
				}
				return segs.sort(compareSlotSegs);
			}
			function slotEventEnd(event) {
				if (event.end) {
					return cloneDate(event.end);
				}else{
					return addMinutes(cloneDate(event.start), opt('defaultEventMinutes'));
				}
			}
			function renderSlotSegs(segs, modifiedEventId) {
				var i, segCnt=segs.length, seg,
					event,
					top,
					bottom,
					columnLeft,
					columnRight,
					columnWidth,
					width,
					left,
					right,
					html = '',
					eventElements,
					eventElement,
					triggerRes,
					titleElement,
					height,
					slotSegmentContainer = getSlotSegmentContainer(),
					isRTL = opt('isRTL');
				for (i=0; i<segCnt; i++) {
					seg = segs[i];
					event = seg.event;
					top = timePosition(seg.start, seg.start);
					bottom = timePosition(seg.start, seg.end);
					columnLeft = colContentLeft(seg.col);
					columnRight = colContentRight(seg.col);
					columnWidth = columnRight - columnLeft;
					columnRight -= columnWidth * .025;
					columnWidth = columnRight - columnLeft;
					width = columnWidth * (seg.forwardCoord - seg.backwardCoord);
					if (opt('slotEventOverlap')) {
						width = Math.max(
							(width - (20/2)) * 2,
							width // narrow columns will want to make the segment smaller than
						);
					}
					if (isRTL) {
						right = columnRight - seg.backwardCoord * columnWidth;
						left = right - width;
					}
					else {
						left = columnLeft + seg.backwardCoord * columnWidth;
						right = left + width;
					}
					left = Math.max(left, columnLeft);
					right = Math.min(right, columnRight);
					width = right - left;
					seg.top = top;
					seg.left = left;
					seg.outerWidth = width;
					seg.outerHeight = bottom - top;
					if(event.showBg == '1'){
						html += slotSegHtml(event, seg);
					}
				}
				slotSegmentContainer[0].innerHTML = html; // faster than html()
				eventElements = slotSegmentContainer.children();
				for (i=0; i<segCnt; i++) {
					seg = segs[i];
					event = seg.event;
					eventElement = $(eventElements[i]); // faster than eq()
					triggerRes = trigger('eventRender', event, event, eventElement);
					if (triggerRes === false) {
						eventElement.remove();
					}else{
						if (triggerRes && triggerRes !== true) {
							eventElement.remove();
							eventElement = $(triggerRes).css({
									position: 'absolute',
									top: seg.top,
									left: seg.left
								}).appendTo(slotSegmentContainer);
						}
						seg.element = eventElement;
						if (event._id === modifiedEventId) {
							bindSlotSeg(event, eventElement, seg);
						}else{
							if(!isNull(eventElement[0])){
								eventElement[0]._fci = i; // for lazySegBind
							}
						}
						reportEventElement(event, eventElement);
					}
				}
				lazySegBind(slotSegmentContainer, segs, bindSlotSeg);
				for (i=0; i<segCnt; i++) {
					seg = segs[i];
					if (eventElement = seg.element) {
						seg.vsides = vsides(eventElement, true);
						seg.hsides = hsides(eventElement, true);
						titleElement = eventElement.find('.fc-event-title');
						if (titleElement.length) {
							seg.contentTop = titleElement[0].offsetTop;
						}
					}
				}
				for (i=0; i<segCnt; i++) {
					seg = segs[i];
					if (eventElement = seg.element) {
						eventElement[0].style.width = Math.max(0, seg.outerWidth - seg.hsides) + 'px';
						height = Math.max(0, seg.outerHeight - seg.vsides);
						eventElement[0].style.height = height + 'px';
						event = seg.event;
						if (seg.contentTop !== undefined && height - seg.contentTop < 10) {
							eventElement.find('div.fc-event-time').text(formatDate(event.start, opt('timeFormat')) + ' - ' + event.title);
							eventElement.find('div.fc-event-title').remove();
						}
						trigger('eventAfterRender', event, event, eventElement);
					}
				}
			}
			function slotSegHtml(event, seg) {
				var html = "<";
				var url = event.url;
				var skinCss = getSkinCss(event, opt);
				var classes = ['fc-event', 'fc-event-vert'];
				if (isEventDraggable(event)) {
					classes.push('fc-event-draggable');
				}
				if (seg.isStart) {
					classes.push('fc-event-start');
				}
				if (seg.isEnd) {
					classes.push('fc-event-end');
				}
				classes = classes.concat(event.className);
				if (event.source) {
					classes = classes.concat(event.source.className || []);
				}
				if (url) {
					html += "a href='" + htmlEscape(event.url) + "'";
				}else{
					html += "div";
				}
				html += " class='" + classes.join(' ') + "'" +
					" style=" +
					"'" +
					"position:absolute;" +
					"top:" + seg.top + "px;" +
					"left:" + seg.left + "px;" + skinCss + "'" +
					">" +
					"<div class='fc-event-inner' isDrag='" + event.isDrag + "'>"
					+ "<div class='fc-event-time'>" +
					htmlEscape(formatDates(event.start, event.end, opt('timeFormat'))) +
					"</div>" +
					"<div class='fc-event-title'>" +
					htmlEscape(event.title || '') +
					"</div>" +
					"</div>" +
					"<div class='fc-event-bg'></div>";
				if (seg.isEnd && isEventResizable(event)) {
					html += "<div class='ui-resizable-handle ui-resizable-s'>=</div>";
				}
				html += "</" + (url ? "a" : "div") + ">";
				return html;
			}
			function bindSlotSeg(event, eventElement, seg) {
				var timeElement = eventElement.find('div.fc-event-time');
				if (isEventDraggable(event)) {
					draggableSlotEvent(event, eventElement, timeElement);
				}
				if (seg.isEnd && isEventResizable(event)) {
					resizableSlotEvent(event, eventElement, timeElement);
				}
				eventElementHandlers(event, eventElement);
			}
			/* Dragging-----------------------------------------------------------------------------------*/
			function draggableDayEvent(event, eventElement, seg) {
				var isStart = seg.isStart;
				var origWidth;
				var revert;
				var allDay = true;
				var dayDelta;
				var hoverListener = getHoverListener();
				var colWidth = getColWidth();
				var snapHeight = getSnapHeight();
				var snapMinutes = getSnapMinutes();
				var minMinute = getMinMinute();
				eventElement.draggable({
					opacity: opt('dragOpacity', 'month'), // use whatever the month view was using
					revertDuration: opt('dragRevertDuration'),
					start: function(ev, ui) {
						trigger('eventDragStart', eventElement, event, ev, ui);
						hideEvents(event, eventElement);
						origWidth = eventElement.width();
						hoverListener.start(function(cell, origCell) {
							clearOverlays();
							if (cell) {
								revert = false;
								var origDate = cellToDate(0, origCell.col);
								var date = cellToDate(0, cell.col);
								dayDelta = dayDiff(date, origDate);
								if (!cell.row) {
									renderDayOverlay(
										addDays(cloneDate(event.start), dayDelta),
										addDays(exclEndDay(event), dayDelta)
									);
									resetElement();
								}else{
									if (isStart) {
										if (allDay) {
											eventElement.width(colWidth - 10); // don't use entire width
											setOuterHeight(
												eventElement,
												snapHeight * Math.round(
													(event.end ? ((event.end - event.start) / MINUTE_MS) : opt('defaultEventMinutes')) /
														snapMinutes
												)
											);
											eventElement.draggable('option', 'grid', [colWidth, 1]);
											allDay = false;
										}
									}else{
										revert = true;
									}
								}
								revert = revert || (allDay && !dayDelta);
							}else{
								resetElement();
								revert = true;
							}
							eventElement.draggable('option', 'revert', revert);
						}, ev, 'drag');
					},
					stop: function(ev, ui) {
						hoverListener.stop();
						clearOverlays();
						trigger('eventDragStop', eventElement, event, ev, ui);
						if (revert) {
							resetElement();
							eventElement.css('filter', ''); // clear IE opacity side-effects
							showEvents(event, eventElement);
						}else{
							var minuteDelta = 0;
							if (!allDay) {
								minuteDelta = Math.round((eventElement.offset().top - getSlotContainer().offset().top) / snapHeight)
									* snapMinutes
									+ minMinute
									- (event.start.getHours() * 60 + event.start.getMinutes());
							}
							eventDrop(this, event, dayDelta, minuteDelta, allDay, ev, ui);
						}
					}
				});
				function resetElement() {
					if (!allDay) {
						eventElement
							.width(origWidth)
							.height('')
							.draggable('option', 'grid', null);
						allDay = true;
					}
				}
			}
			function draggableSlotEvent(event, eventElement, timeElement) {
				var coordinateGrid = t.getCoordinateGrid();
				var colCnt = getColCnt();
				var colWidth = getColWidth();
				var snapHeight = getSnapHeight();
				var snapMinutes = getSnapMinutes();
				var origPosition; // original position of the element, not the mouse
				var origCell;
				var isInBounds, prevIsInBounds;
				var isAllDay, prevIsAllDay;
				var colDelta, prevColDelta;
				var dayDelta; // derived from colDelta
				var minuteDelta, prevMinuteDelta;
				eventElement.draggable({
					scroll: false,
					grid: [ colWidth, snapHeight ],
					axis: colCnt==1 ? 'y' : false,
					opacity: opt('dragOpacity'),
					revertDuration: opt('dragRevertDuration'),
					start: function(ev, ui) {
						trigger('eventDragStart', eventElement, event, ev, ui);
						hideEvents(event, eventElement);
						coordinateGrid.build();
						// initialize states
						origPosition = eventElement.position();
						origCell = coordinateGrid.cell(ev.pageX, ev.pageY);
						isInBounds = prevIsInBounds = true;
						isAllDay = prevIsAllDay = getIsCellAllDay(origCell);
						colDelta = prevColDelta = 0;
						dayDelta = 0;
						minuteDelta = prevMinuteDelta = 0;
					},
					drag: function(ev, ui) {
						var cell = coordinateGrid.cell(ev.pageX, ev.pageY);
						isInBounds = !!cell;
						if (isInBounds) {
							isAllDay = getIsCellAllDay(cell);
							colDelta = Math.round((ui.position.left - origPosition.left) / colWidth);
							if (colDelta != prevColDelta) {
								var origDate = cellToDate(0, origCell.col);
								var col = origCell.col + colDelta;
								col = Math.max(0, col);
								col = Math.min(colCnt-1, col);
								var date = cellToDate(0, col);
								dayDelta = dayDiff(date, origDate);
							}
							if (!isAllDay) {
								minuteDelta = Math.round((ui.position.top - origPosition.top) / snapHeight) * snapMinutes;
							}
						}
						if (isInBounds != prevIsInBounds || isAllDay != prevIsAllDay || colDelta != prevColDelta || minuteDelta != prevMinuteDelta) {
							updateUI();
							prevIsInBounds = isInBounds;
							prevIsAllDay = isAllDay;
							prevColDelta = colDelta;
							prevMinuteDelta = minuteDelta;
						}
						eventElement.draggable('option', 'revert', !isInBounds);
					},
					stop: function(ev, ui) {
						clearOverlays();
						trigger('eventDragStop', eventElement, event, ev, ui);
						if (isInBounds && (isAllDay || dayDelta || minuteDelta)) { // changed!
							eventDrop(this, event, dayDelta, isAllDay ? 0 : minuteDelta, isAllDay, ev, ui);
						} else { // either no change or out-of-bounds (draggable has already reverted)
							isInBounds = true;
							isAllDay = false;
							colDelta = 0;
							dayDelta = 0;
							minuteDelta = 0;
							updateUI();
							eventElement.css('filter', ''); // clear IE opacity side-effects
							eventElement.css(origPosition);
							showEvents(event, eventElement);
						}
					}
				});
				function updateUI() {
					clearOverlays();
					if (isInBounds) {
						if (isAllDay) {
							timeElement.hide();
							eventElement.draggable('option', 'grid', null); // disable grid snapping
							renderDayOverlay(
								addDays(cloneDate(event.start), dayDelta),
								addDays(exclEndDay(event), dayDelta)
							);
						} else {
							updateTimeText(minuteDelta);
							timeElement.css('display', ''); // show() was causing display=inline
							eventElement.draggable('option', 'grid', [colWidth, snapHeight]); // re-enable grid snapping
						}
					}
				}
				function updateTimeText(minuteDelta) {
					var newStart = addMinutes(cloneDate(event.start), minuteDelta);
					var newEnd;
					if (event.end) {
						newEnd = addMinutes(cloneDate(event.end), minuteDelta);
					}
					timeElement.text(formatDates(newStart, newEnd, opt('timeFormat')));
				}
			}
			function resizableSlotEvent(event, eventElement, timeElement) {
				var snapDelta, prevSnapDelta;
				var snapHeight = getSnapHeight();
				var snapMinutes = getSnapMinutes();
				eventElement.resizable({
					handles: {
						s: '.ui-resizable-handle'
					},
					grid: snapHeight,
					start: function(ev, ui) {
						snapDelta = prevSnapDelta = 0;
						hideEvents(event, eventElement);
						trigger('eventResizeStart', this, event, ev, ui);
					},
					resize: function(ev, ui) {
						snapDelta = Math.round((Math.max(snapHeight, eventElement.height()) - ui.originalSize.height) / snapHeight);
						if (snapDelta != prevSnapDelta) {
							timeElement.text(
								formatDates(
									event.start,
									(!snapDelta && !event.end) ? null : // no change, so don't display time range
										addMinutes(eventEnd(event), snapMinutes*snapDelta),
									opt('timeFormat')
								)
							);
							prevSnapDelta = snapDelta;
						}
					},
					stop: function(ev, ui) {
						trigger('eventResizeStop', this, event, ev, ui);
						if (snapDelta) {
							eventResize(this, event, 0, snapMinutes*snapDelta, ev, ui);
						}else{
							showEvents(event, eventElement);
						}
					}
				});
			}
		}
		function placeSlotSegs(segs) {
			var levels = buildSlotSegLevels(segs);
			var level0 = levels[0];
			var i;
			computeForwardSlotSegs(levels);
			if (level0) {
				for (i=0; i<level0.length; i++) {
					computeSlotSegPressures(level0[i]);
				}
				for (i=0; i<level0.length; i++) {
					computeSlotSegCoords(level0[i], 0, 0);
				}
			}
			return flattenSlotSegLevels(levels);
		}
		function buildSlotSegLevels(segs) {
			var levels = [];
			var i, seg;
			var j;
			for (i=0; i<segs.length; i++) {
				seg = segs[i];
				for (j=0; j<levels.length; j++) {
					if (!computeSlotSegCollisions(seg, levels[j]).length) {
						break;
					}
				}
				(levels[j] || (levels[j] = [])).push(seg);
			}
			return levels;
		}
		function computeForwardSlotSegs(levels) {
			var i, level;
			var j, seg;
			var k;
			for (i=0; i<levels.length; i++) {
				level = levels[i];
				for (j=0; j<level.length; j++) {
					seg = level[j];
					seg.forwardSegs = [];
					for (k=i+1; k<levels.length; k++) {
						computeSlotSegCollisions(seg, levels[k], seg.forwardSegs);
					}
				}
			}
		}
		function computeSlotSegPressures(seg) {
			var forwardSegs = seg.forwardSegs;
			var forwardPressure = 0;
			var i, forwardSeg;
			if (seg.forwardPressure === undefined) { // not already computed
				for (i=0; i<forwardSegs.length; i++) {
					forwardSeg = forwardSegs[i];
					computeSlotSegPressures(forwardSeg);
					forwardPressure = Math.max(
						forwardPressure,
						1 + forwardSeg.forwardPressure
					);
				}
				seg.forwardPressure = forwardPressure;
			}
		}
		function computeSlotSegCoords(seg, seriesBackwardPressure, seriesBackwardCoord) {
			var forwardSegs = seg.forwardSegs;
			var i;
			if (seg.forwardCoord === undefined) { // not already computed
				if (!forwardSegs.length) {
					seg.forwardCoord = 1;
				} else {
					forwardSegs.sort(compareForwardSlotSegs);
					computeSlotSegCoords(forwardSegs[0], seriesBackwardPressure + 1, seriesBackwardCoord);
					seg.forwardCoord = forwardSegs[0].backwardCoord;
				}
				seg.backwardCoord = seg.forwardCoord -
					(seg.forwardCoord - seriesBackwardCoord) / // available width for series
					(seriesBackwardPressure + 1); // # of segments in the series
				for (i=0; i<forwardSegs.length; i++) {
					computeSlotSegCoords(forwardSegs[i], 0, seg.forwardCoord);
				}
			}
		}
		function flattenSlotSegLevels(levels) {
			var segs = [];
			var i, level;
			var j;
			for (i=0; i<levels.length; i++) {
				level = levels[i];
				for (j=0; j<level.length; j++) {
					segs.push(level[j]);
				}
			}
			return segs;
		}
		function computeSlotSegCollisions(seg, otherSegs, results) {
			results = results || [];
			for (var i=0; i<otherSegs.length; i++) {
				if (isSlotSegCollision(seg, otherSegs[i])) {
					results.push(otherSegs[i]);
				}
			}
			return results;
		}
		function isSlotSegCollision(seg1, seg2) {
			return seg1.end > seg2.start && seg1.start < seg2.end;
		}
		function compareForwardSlotSegs(seg1, seg2) {
			return seg2.forwardPressure - seg1.forwardPressure ||
				(seg1.backwardCoord || 0) - (seg2.backwardCoord || 0) ||
				compareSlotSegs(seg1, seg2);
		}
		function compareSlotSegs(seg1, seg2) {
			return seg1.start - seg2.start || // earlier start time goes first
				(seg2.end - seg2.start) - (seg1.end - seg1.start) || // tie? longer-duration goes first
				(seg1.event.title || '').localeCompare(seg2.event.title); // tie? alphabetically by title
		}
		;;
		function View(element, calendar, viewName) {
			var t = this;
			t.element = element;
			t.calendar = calendar;
			t.name = viewName;
			t.opt = opt;
			t.trigger = trigger;
			t.isEventDraggable = isEventDraggable;
			t.isEventResizable = isEventResizable;
			t.setEventData = setEventData;
			t.clearEventData = clearEventData;
			t.eventEnd = eventEnd;
			t.reportEventElement = reportEventElement;
			t.triggerEventDestroy = triggerEventDestroy;
			t.eventElementHandlers = eventElementHandlers;
			t.showEvents = showEvents;
			t.hideEvents = hideEvents;
			t.eventDrop = eventDrop;
			t.eventResize = eventResize;
			var defaultEventEnd = t.defaultEventEnd;
			var normalizeEvent = calendar.normalizeEvent; // in EventManager
			var reportEventChange = calendar.reportEventChange;
			var eventsByID = {}; // eventID mapped to array of events (there can be multiple b/c of repeating events)
			var eventElementsByID = {}; // eventID mapped to array of jQuery elements
			var eventElementCouples = []; // array of objects, { event, element } // TODO: unify with segment system
			var options = calendar.options;
			function opt(name, viewNameOverride) {
				var v = options[name];
				if ($.isPlainObject(v)) {
					return smartProperty(v, viewNameOverride || viewName);
				}
				return v;
			}
			function trigger(name, thisObj) {
				return calendar.trigger.apply(
					calendar,
					[name, thisObj || t].concat(Array.prototype.slice.call(arguments, 2), [t])
				);
			}
			function isEventDraggable(event) {
				var source = event.source || {};
				return firstDefined(
						event.startEditable,
						source.startEditable,
						opt('eventStartEditable'),
						event.editable,
						source.editable,
						opt('editable')
					)
					&& !opt('disableDragging'); // deprecated
			}
			function isEventResizable(event) { // but also need to make sure the seg.isEnd == true
				var source = event.source || {};
				return firstDefined(
						event.durationEditable,
						source.durationEditable,
						opt('eventDurationEditable'),
						event.editable,
						source.editable,
						opt('editable')
					)
					&& !opt('disableResizing'); // deprecated
			}
			function setEventData(events) { // events are already normalized at this point
				eventsByID = {};
				var i, len=events.length, event;
				for (i=0; i<len; i++) {
					event = events[i];
					if (eventsByID[event._id]) {
						eventsByID[event._id].push(event);
					}else{
						eventsByID[event._id] = [event];
					}
				}
			}
			function clearEventData() {
				eventsByID = {};
				eventElementsByID = {};
				eventElementCouples = [];
			}
			function eventEnd(event) {
				return event.end ? cloneDate(event.end) : defaultEventEnd(event);
			}
			function reportEventElement(event, element) {
				eventElementCouples.push({ event: event, element: element });
				if (eventElementsByID[event._id]) {
					eventElementsByID[event._id].push(element);
				}else{
					eventElementsByID[event._id] = [element];
				}
			}
			function triggerEventDestroy() {
				$.each(eventElementCouples, function(i, couple) {
					t.trigger('eventDestroy', couple.event, couple.event, couple.element);
				});
			}
			function eventElementHandlers(event, eventElement) {
				eventElement.click(function(ev) {
					if (!eventElement.hasClass('ui-draggable-dragging') && !eventElement.hasClass('ui-resizable-resizing')) {
						return trigger('eventClick', this, event, ev);
					}
				}).hover(
					function(ev) {
						trigger('eventMouseover', this, event, ev);
					},
					function(ev) {
						trigger('eventMouseout', this, event, ev);
					}
				);
			}
			function showEvents(event, exceptElement) {
				eachEventElement(event, exceptElement, 'show');
			}
			function hideEvents(event, exceptElement) {
				eachEventElement(event, exceptElement, 'hide');
			}
			function eachEventElement(event, exceptElement, funcName) {
				var elements = eventElementsByID[event._id],
					i, len = elements.length;
				for (i=0; i<len; i++) {
					if (!exceptElement || elements[i][0] != exceptElement[0]) {
						elements[i][funcName]();
					}
				}
			}
			function eventDrop(e, event, dayDelta, minuteDelta, allDay, ev, ui) {
				var oldAllDay = event.allDay;
				var eventId = event._id;
				moveEvents(eventsByID[eventId], dayDelta, minuteDelta, allDay);
				trigger(
					'eventDrop',
					e,
					event,
					dayDelta,
					minuteDelta,
					allDay,
					function() {
						moveEvents(eventsByID[eventId], -dayDelta, -minuteDelta, oldAllDay);
						reportEventChange(eventId);
					}, ev, ui
				);
				reportEventChange(eventId);
			}
			
			function eventResize(e, event, dayDelta, minuteDelta, ev, ui) {
				var eventId = event._id;
				elongateEvents(eventsByID[eventId], dayDelta, minuteDelta);
				trigger(
					'eventResize',
					e,
					event,
					dayDelta,
					minuteDelta,
					function() {
						elongateEvents(eventsByID[eventId], -dayDelta, -minuteDelta);
						reportEventChange(eventId);
					},
					ev,
					ui
				);
				reportEventChange(eventId);
			}
			function moveEvents(events, dayDelta, minuteDelta, allDay) {
				minuteDelta = minuteDelta || 0;
				for (var e, len=events.length, i=0; i<len; i++) {
					e = events[i];
					if (allDay !== undefined) {
						e.allDay = allDay;
					}
					addMinutes(addDays(e.start, dayDelta, true), minuteDelta);
					if (e.end) {
						e.end = addMinutes(addDays(e.end, dayDelta, true), minuteDelta);
					}
					normalizeEvent(e, options);
				}
			}
			function elongateEvents(events, dayDelta, minuteDelta) {
				minuteDelta = minuteDelta || 0;
				for (var e, len=events.length, i=0; i<len; i++) {
					e = events[i];
					e.end = addMinutes(addDays(eventEnd(e), dayDelta, true), minuteDelta);
					normalizeEvent(e, options);
				}
			}
			t.isHiddenDay = isHiddenDay;
			t.skipHiddenDays = skipHiddenDays;
			t.getCellsPerWeek = getCellsPerWeek;
			t.dateToCell = dateToCell;
			t.dateToDayOffset = dateToDayOffset;
			t.dayOffsetToCellOffset = dayOffsetToCellOffset;
			t.cellOffsetToCell = cellOffsetToCell;
			t.cellToDate = cellToDate;
			t.cellToCellOffset = cellToCellOffset;
			t.cellOffsetToDayOffset = cellOffsetToDayOffset;
			t.dayOffsetToDate = dayOffsetToDate;
			t.rangeToSegments = rangeToSegments;
			var hiddenDays = opt('hiddenDays') || []; // array of day-of-week indices that are hidden
			var isHiddenDayHash = []; // is the day-of-week hidden? (hash with day-of-week-index -> bool)
			var cellsPerWeek;
			var dayToCellMap = []; // hash from dayIndex -> cellIndex, for one week
			var cellToDayMap = []; // hash from cellIndex -> dayIndex, for one week
			var isRTL = opt('isRTL');
			(function() {
				if (opt('weekends') === false) {
					hiddenDays.push(0, 6); // 0=sunday, 6=saturday
				}
				for (var dayIndex=0, cellIndex=0; dayIndex<7; dayIndex++) {
					dayToCellMap[dayIndex] = cellIndex;
					isHiddenDayHash[dayIndex] = $.inArray(dayIndex, hiddenDays) != -1;
					if (!isHiddenDayHash[dayIndex]) {
						cellToDayMap[cellIndex] = dayIndex;
						cellIndex++;
					}
				}
				cellsPerWeek = cellIndex;
				if (!cellsPerWeek) {
					throw 'invalid hiddenDays'; // all days were hidden? bad.
				}
			})();
			function isHiddenDay(day) {
				if (typeof day == 'object') {
					day = day.getDay();
				}
				return isHiddenDayHash[day];
			}
			function getCellsPerWeek() {
				return cellsPerWeek;
			}
			function skipHiddenDays(date, inc, isExclusive) {
				inc = inc || 1;
				while (
					isHiddenDayHash[ ( date.getDay() + (isExclusive ? inc : 0) + 7 ) % 7 ]
				) {
					addDays(date, inc);
				}
			}
			function cellToDate() {
				var cellOffset = cellToCellOffset.apply(null, arguments);
				var dayOffset = cellOffsetToDayOffset(cellOffset);
				var date = dayOffsetToDate(dayOffset);
				return date;
			}
			function cellToCellOffset(row, col) {
				var colCnt = t.getColCnt();
				var dis = isRTL ? -1 : 1;
				var dit = isRTL ? colCnt - 1 : 0;
				if (typeof row == 'object') {
					col = row.col;
					row = row.row;
				}
				var cellOffset = row * colCnt + (col * dis + dit); // column, adjusted for RTL (dis & dit)
				return cellOffset;
			}
			function cellOffsetToDayOffset(cellOffset) {
				var day0 = t.visStart.getDay(); // first date's day of week
				cellOffset += dayToCellMap[day0]; // normlize cellOffset to beginning-of-week
				return Math.floor(cellOffset / cellsPerWeek) * 7 // # of days from full weeks
					+ cellToDayMap[ // # of days from partial last week
						(cellOffset % cellsPerWeek + cellsPerWeek) % cellsPerWeek // crazy math to handle negative cellOffsets
					]
					- day0; // adjustment for beginning-of-week normalization
			}
			function dayOffsetToDate(dayOffset) {
				var date = cloneDate(t.visStart);
				addDays(date, dayOffset);
				return date;
			}
			function dateToCell(date) {
				var dayOffset = dateToDayOffset(date);
				var cellOffset = dayOffsetToCellOffset(dayOffset);
				var cell = cellOffsetToCell(cellOffset);
				return cell;
			}
			function dateToDayOffset(date) {
				return dayDiff(date, t.visStart);
			}
			function dayOffsetToCellOffset(dayOffset) {
				var day0 = t.visStart.getDay(); // first date's day of week
				dayOffset += day0; // normalize dayOffset to beginning-of-week
				return Math.floor(dayOffset / 7) * cellsPerWeek // # of cells from full weeks
					+ dayToCellMap[ // # of cells from partial last week
						(dayOffset % 7 + 7) % 7 // crazy math to handle negative dayOffsets
					]
					- dayToCellMap[day0]; // adjustment for beginning-of-week normalization
			}
			function cellOffsetToCell(cellOffset) {
				var colCnt = t.getColCnt();
				var dis = isRTL ? -1 : 1;
				var dit = isRTL ? colCnt - 1 : 0;
				var row = Math.floor(cellOffset / colCnt);
				var col = ((cellOffset % colCnt + colCnt) % colCnt) * dis + dit; // column, adjusted for RTL (dis & dit)
				return {row: row, col: col};
			}
			function rangeToSegments(startDate, endDate) {
				var rowCnt = t.getRowCnt();
				var colCnt = t.getColCnt();
				var segments = []; // array of segments to return
				var rangeDayOffsetStart = dateToDayOffset(startDate);
				var rangeDayOffsetEnd = dateToDayOffset(endDate); // exclusive
				var rangeCellOffsetFirst = dayOffsetToCellOffset(rangeDayOffsetStart);
				var rangeCellOffsetLast = dayOffsetToCellOffset(rangeDayOffsetEnd) - 1;
				for (var row=0; row<rowCnt; row++) {
					var rowCellOffsetFirst = row * colCnt;
					var rowCellOffsetLast = rowCellOffsetFirst + colCnt - 1;
					var segmentCellOffsetFirst = Math.max(rangeCellOffsetFirst, rowCellOffsetFirst);
					var segmentCellOffsetLast = Math.min(rangeCellOffsetLast, rowCellOffsetLast);
					if (segmentCellOffsetFirst <= segmentCellOffsetLast) {
						var segmentCellFirst = cellOffsetToCell(segmentCellOffsetFirst);
						var segmentCellLast = cellOffsetToCell(segmentCellOffsetLast);
						var cols = [ segmentCellFirst.col, segmentCellLast.col ].sort();
						var isStart = cellOffsetToDayOffset(segmentCellOffsetFirst) == rangeDayOffsetStart;
						var isEnd = cellOffsetToDayOffset(segmentCellOffsetLast) + 1 == rangeDayOffsetEnd; // +1 for comparing exclusively
						segments.push({
							row: row,
							leftCol: cols[0],
							rightCol: cols[1],
							isStart: isStart,
							isEnd: isEnd
						});
					}
				}
				return segments;
			}
		}
		;;
		function DayEventRenderer() {//天的事件初始化
			var t = this;
			// exports
			t.renderDayEvents = renderDayEvents;
			t.draggableDayEvent = draggableDayEvent; // made public so that subclasses can override
			t.resizableDayEvent = resizableDayEvent; // "
			var opt = t.opt;
			var trigger = t.trigger;
			var isEventDraggable = t.isEventDraggable;
			var isEventResizable = t.isEventResizable;
			var eventEnd = t.eventEnd;
			var reportEventElement = t.reportEventElement;
			var eventElementHandlers = t.eventElementHandlers;
			var showEvents = t.showEvents;
			var hideEvents = t.hideEvents;
			var eventDrop = t.eventDrop;
			var eventResize = t.eventResize;
			var getRowCnt = t.getRowCnt;
			var getColCnt = t.getColCnt;
			var getColWidth = t.getColWidth;
			var allDayRow = t.allDayRow;
			var colLeft = t.colLeft;
			var colRight = t.colRight;
			var colContentLeft = t.colContentLeft;
			var colContentRight = t.colContentRight;
			var dateToCell = t.dateToCell;
			var getDaySegmentContainer = t.getDaySegmentContainer;
			var formatDates = t.calendar.formatDates;
			var renderDayOverlay = t.renderDayOverlay;
			var clearOverlays = t.clearOverlays;
			var clearSelection = t.clearSelection;
			var getHoverListener = t.getHoverListener;
			var rangeToSegments = t.rangeToSegments;
			var cellToDate = t.cellToDate;
			var cellToCellOffset = t.cellToCellOffset;
			var cellOffsetToDayOffset = t.cellOffsetToDayOffset;
			var dateToDayOffset = t.dateToDayOffset;
			var dayOffsetToCellOffset = t.dayOffsetToCellOffset;
			function renderDayEvents(events, modifiedEventId) {
				var segments = _renderDayEvents(
					events,
					false, // don't append event elements
					true // set the heights of the rows
				);
				segmentElementEach(segments, function(segment, element) {
					reportEventElement(segment.event, element);
				});
				attachHandlers(segments, modifiedEventId);
				segmentElementEach(segments, function(segment, element) {
					trigger('eventAfterRender', segment.event, segment.event, element);
				});
			}
			function renderTempDayEvent(event, adjustRow, adjustTop) {
				var segments = _renderDayEvents(
					[ event ],
					true, // append event elements
					false // don't set the heights of the rows
				);
				var elements = [];
				segmentElementEach(segments, function(segment, element) {
					if (segment.row === adjustRow) {
						element.css('top', adjustTop);
					}
					elements.push(element[0]); // accumulate DOM nodes
				});
				return elements;
			}
			function _renderDayEvents(events, doAppend, doRowHeights) {//加载天的日程信息
				var finalContainer = getDaySegmentContainer();
				var renderContainer = doAppend ? $("<div/>") : finalContainer;
				var segments = buildSegments(events);
				var html;
				var elements;
				calculateHorizontals(segments);
				html = buildHTML(segments);
				renderContainer[0].innerHTML = html;
				elements = renderContainer.children();
				if (doAppend) {
					finalContainer.append(elements);
				}
				resolveElements(segments, elements);
				segmentElementEach(segments, function(segment, element) {
					segment.hsides = hsides(element, true); // include margins = `true`
				});
				segmentElementEach(segments, function(segment, element) {
					element.width(Math.max(0, segment.outerWidth - segment.hsides));
				});
				segmentElementEach(segments, function(segment, element) {
					segment.outerHeight = element.outerHeight(true); // include margins = `true`
				});
				setVerticals(segments, doRowHeights);
				return segments;
			}
			function buildSegments(events) {//绑定事件
				var segments = [];
				for (var i=0; i<events.length; i++) {
					if(events[i].showBg == '1'){
						var eventSegments = buildSegmentsForEvent(events[i]);
						segments.push.apply(segments, eventSegments); // append an array to an array
					}
				}
				return segments;
			}
			function buildSegmentsForEvent(event) {
				var startDate = event.start;
				var endDate = exclEndDay(event);
				var segments = rangeToSegments(startDate, endDate);
				for (var i=0; i<segments.length; i++) {
					segments[i].event = event;
				}
				return segments;
			}
			function calculateHorizontals(segments) {
				var isRTL = opt('isRTL');
				for (var i=0; i<segments.length; i++) {
					var segment = segments[i];
					var leftFunc = (isRTL ? segment.isEnd : segment.isStart) ? colContentLeft : colLeft;
					var rightFunc = (isRTL ? segment.isStart : segment.isEnd) ? colContentRight : colRight;
					var left = leftFunc(segment.leftCol);
					var right = rightFunc(segment.rightCol);
					segment.left = left;
					segment.outerWidth = right - left;
				}
			}
			function buildHTML(segments) {
				var html = '';
				for (var i=0; i<segments.length; i++) {
					html += buildHTMLForSegment(segments[i]);
				}
				return html;
			}
			function buildHTMLForSegment(segment) {
				var html = '';
				var isRTL = opt('isRTL');
				var event = segment.event;
				var url = event.url;
				var classNames = [ 'fc-event', 'fc-event-hori' ];
				if (isEventDraggable(event)) {
					classNames.push('fc-event-draggable');
				}
				if (segment.isStart) {
					classNames.push('fc-event-start');
				}
				if (segment.isEnd) {
					classNames.push('fc-event-end');
				}
				classNames = classNames.concat(event.className);
				if (event.source) {
					classNames = classNames.concat(event.source.className || []);
				}
				var skinCss = getSkinCss(event, opt);
				if (url) {
					html += "<a href='" + htmlEscape(url) + "'";
				}else{
					html += "<div";
				}
				html += " class='" + classNames.join(' ') + "'" +
				" style=" +
				"'" +
				"position:absolute;" +
				"left:" + segment.left + "px;" +
				skinCss +
				"'" +
				">" +
				"<div class='fc-event-inner' isDrag='" + event.isDrag + "'>";
				if (!event.allDay && segment.isStart) {
					html += "<span class='fc-event-time'>" +
					htmlEscape(formatDates(event.start, event.end, opt('timeFormat'))) +
					"</span>";
				}
				html += "<span class='fc-event-title'>" +
				htmlEscape(event.title || '') +
				"</span>" +
				"</div>";
				if (segment.isEnd && isEventResizable(event)) {
					html += "<div class='ui-resizable-handle ui-resizable-" + (isRTL ? 'w' : 'e') + "'>" +
					"&nbsp;&nbsp;&nbsp;" + // makes hit area a lot better for IE6/7
					"</div>";
				}
				html += "</" + (url ? "a" : "div") + ">";
				return html;
			}
			function resolveElements(segments, elements) {
				for (var i=0; i<segments.length; i++) {
					var segment = segments[i];
					var event = segment.event;
					var element = elements.eq(i);
					var triggerRes = trigger('eventRender', event, event, element);
					if (triggerRes === false) {
						element.remove();
					} else {
						if (triggerRes && triggerRes !== true) {
							triggerRes = $(triggerRes).css({position: 'absolute', left: segment.left});
							element.replaceWith(triggerRes);
							element = triggerRes;
						}
						segment.element = element;
					}
				}
			}
			function setVerticals(segments, doRowHeights) {
				var rowContentHeights = calculateVerticals(segments); // also sets segment.top
				var rowContentElements = getRowContentElements(); // returns 1 inner div per row
				var rowContentTops = [];
				if (doRowHeights) {
					for (var i=0; i<rowContentElements.length; i++) {
						rowContentElements[i].height(rowContentHeights[i]);
					}
				}
				for (var i=0; i<rowContentElements.length; i++) {
					rowContentTops.push(rowContentElements[i].position().top);
				}
				segmentElementEach(segments, function(segment, element) {
					element.css('top', rowContentTops[segment.row] + segment.top);
				});
			}
			function calculateVerticals(segments) {
				var rowCnt = getRowCnt();
				var colCnt = getColCnt();
				var rowContentHeights = []; // content height for each row
				var segmentRows = buildSegmentRows(segments); // an array of segment arrays, one for each row
				for (var rowI=0; rowI<rowCnt; rowI++) {
					var segmentRow = segmentRows[rowI];
					var colHeights = [];
					for (var colI=0; colI<colCnt; colI++) {
						colHeights.push(0);
					}
					for (var segmentI=0; segmentI<segmentRow.length; segmentI++) {
						var segment = segmentRow[segmentI];
						segment.top = arrayMax(
							colHeights.slice(
								segment.leftCol,
								segment.rightCol + 1 // make exclusive for slice
							)
						);
						for (var colI=segment.leftCol; colI<=segment.rightCol; colI++) {
							colHeights[colI] = segment.top + segment.outerHeight;
						}
					}
					rowContentHeights.push(arrayMax(colHeights));
				}
				return rowContentHeights;
			}
			function buildSegmentRows(segments) {
				var rowCnt = getRowCnt();
				var segmentRows = [];
				var segmentI;
				var segment;
				var rowI;
				for (segmentI=0; segmentI<segments.length; segmentI++) {
					segment = segments[segmentI];
					rowI = segment.row;
					if (segment.element) { // was rendered?
						if (segmentRows[rowI]) {
							segmentRows[rowI].push(segment);
						} else {
							segmentRows[rowI] = [ segment ];
						}
					}
				}
				for (rowI=0; rowI<rowCnt; rowI++) {
					segmentRows[rowI] = sortSegmentRow(segmentRows[rowI] || []);
				}
				return segmentRows;
			}
			function sortSegmentRow(segments) {
				var sortedSegments = [];
				var subrows = buildSegmentSubrows(segments);
				for (var i=0; i<subrows.length; i++) {
					sortedSegments.push.apply(sortedSegments, subrows[i]); // append an array to an array
				}
				return sortedSegments;
			}
			function buildSegmentSubrows(segments) {
				segments.sort(compareDaySegments);
				var subrows = [];
				for (var i=0; i<segments.length; i++) {
					var segment = segments[i];
					for (var j=0; j<subrows.length; j++) {
						if (!isDaySegmentCollision(segment, subrows[j])) {
							break;
						}
					}
					if (subrows[j]) {
						subrows[j].push(segment);
					} else {
						subrows[j] = [ segment ];
					}
				}
				return subrows;
			}
			function getRowContentElements() {
				var i;
				var rowCnt = getRowCnt();
				var rowDivs = [];
				for (i=0; i<rowCnt; i++) {
					rowDivs[i] = allDayRow(i).find('div.fc-day-content > div');
				}
				return rowDivs;
			}
			function attachHandlers(segments, modifiedEventId) {
				var segmentContainer = getDaySegmentContainer();
				segmentElementEach(segments, function(segment, element, i) {
					var event = segment.event;
					if (event._id === modifiedEventId) {
						bindDaySeg(event, element, segment);
					}else{
						element[0]._fci = i; // for lazySegBind
					}
				});
				lazySegBind(segmentContainer, segments, bindDaySeg);
			}
			function bindDaySeg(event, eventElement, segment) {
				if (isEventDraggable(event)) {
					t.draggableDayEvent(event, eventElement, segment); // use `t` so subclasses can override
				}
				if (segment.isEnd && isEventResizable(event)) {
					t.resizableDayEvent(event, eventElement, segment); // use `t` so subclasses can override
				}
				eventElementHandlers(event, eventElement);
			}
			function draggableDayEvent(event, eventElement) {
				var hoverListener = getHoverListener();
				var dayDelta;
				eventElement.draggable({
					delay: 50,
					opacity: opt('dragOpacity'),
					revertDuration: opt('dragRevertDuration'),
					start: function(ev, ui) {
						trigger('eventDragStart', eventElement, event, ev, ui);
						hideEvents(event, eventElement);
						hoverListener.start(function(cell, origCell, rowDelta, colDelta) {
							eventElement.draggable('option', 'revert', !cell || !rowDelta && !colDelta);
							clearOverlays();
							if (cell) {
								var origDate = cellToDate(origCell);
								var date = cellToDate(cell);
								dayDelta = dayDiff(date, origDate);
								renderDayOverlay(
									addDays(cloneDate(event.start), dayDelta),
									addDays(exclEndDay(event), dayDelta)
								);
							}else{
								dayDelta = 0;
							}
						}, ev, 'drag');
					},
					stop: function(ev, ui) {
						hoverListener.stop();
						clearOverlays();
						trigger('eventDragStop', eventElement, event, ev, ui);
						if (dayDelta) {
							eventDrop(this, event, dayDelta, 0, event.allDay, ev, ui);
						}else{
							eventElement.css('filter', ''); // clear IE opacity side-effects
							showEvents(event, eventElement);
						}
					}
				});
			}
			function resizableDayEvent(event, element, segment) {
				var isRTL = opt('isRTL');
				var direction = isRTL ? 'w' : 'e';
				var handle = element.find('.ui-resizable-' + direction); // TODO: stop using this class because we aren't using jqui for this
				var isResizing = false;
				disableTextSelection(element); // prevent native <a> selection for IE
				element.mousedown(function(ev) { // prevent native <a> selection for others
						ev.preventDefault();
					}).click(function(ev) {
						if (isResizing) {
							ev.preventDefault(); // prevent link from being visited (only method that worked in IE6)
							ev.stopImmediatePropagation(); // prevent fullcalendar eventClick handler from being called
							                               // (eventElementHandlers needs to be bound after resizableDayEvent)
						}
					});
				handle.mousedown(function(ev) {
					if (ev.which != 1) {
						return; // needs to be left mouse button
					}
					isResizing = true;
					var hoverListener = getHoverListener();
					var rowCnt = getRowCnt();
					var colCnt = getColCnt();
					var elementTop = element.css('top');
					var dayDelta;
					var helpers;
					var eventCopy = $.extend({}, event);
					var minCellOffset = dayOffsetToCellOffset( dateToDayOffset(event.start) );
					clearSelection();
					$('body').css('cursor', direction + '-resize').one('mouseup', mouseup);
					trigger('eventResizeStart', this, event, ev);
					hoverListener.start(function(cell, origCell) {
						if (cell) {
							var origCellOffset = cellToCellOffset(origCell);
							var cellOffset = cellToCellOffset(cell);
							cellOffset = Math.max(cellOffset, minCellOffset);
							dayDelta = cellOffsetToDayOffset(cellOffset) - cellOffsetToDayOffset(origCellOffset);
							if (dayDelta) {
								eventCopy.end = addDays(eventEnd(event), dayDelta, true);
								var oldHelpers = helpers;
								helpers = renderTempDayEvent(eventCopy, segment.row, elementTop);
								helpers = $(helpers); // turn array into a jQuery object
								helpers.find('*').css('cursor', direction + '-resize');
								if (oldHelpers) {
									oldHelpers.remove();
								}
								hideEvents(event);
							} else {
								if (helpers) {
									showEvents(event);
									helpers.remove();
									helpers = null;
								}
							}
							clearOverlays();
							renderDayOverlay( // coordinate grid already rebuilt with hoverListener.start()
								event.start,
								addDays( exclEndDay(event), dayDelta )
							);
						}
					}, ev);
					function mouseup(ev) {
						trigger('eventResizeStop', this, event, ev);
						$('body').css('cursor', '');
						hoverListener.stop();
						clearOverlays();
						if (dayDelta) {
							eventResize(this, event, dayDelta, 0, ev);
						}
						setTimeout(function() { // make this happen after the element's click event
							isResizing = false;
						},0);
					}
				});
			}
		}
		function isDaySegmentCollision(segment, otherSegments) {
			for (var i=0; i<otherSegments.length; i++) {
				var otherSegment = otherSegments[i];
				if (otherSegment.leftCol <= segment.rightCol && otherSegment.rightCol >= segment.leftCol) {
					return true;
				}
			}
			return false;
		}
		function segmentElementEach(segments, callback) {
			for (var i=0; i<segments.length; i++) {
				var segment = segments[i];
				var element = segment.element;
				if (element) {
					callback(segment, element, i);
				}
			}
		}
		function compareDaySegments(a, b) {
			return (b.rightCol - b.leftCol) - (a.rightCol - a.leftCol) || // put wider events first
				b.event.allDay - a.event.allDay || // if tie, put all-day events first (booleans cast to 0/1)
				a.event.start - b.event.start || // if a tie, sort by event start date
				(a.event.title || '').localeCompare(b.event.title) // if a tie, sort by event title
		}
		;;
		function SelectionManager() {
			var t = this;
			t.select = select;
			t.unselect = unselect;
			t.reportSelection = reportSelection;
			t.daySelectionMousedown = daySelectionMousedown;
			var opt = t.opt;
			var trigger = t.trigger;
			var defaultSelectionEnd = t.defaultSelectionEnd;
			var renderSelection = t.renderSelection;
			var clearSelection = t.clearSelection;
			var selected = false;
			if (opt('selectable') && opt('unselectAuto')) {
				$(document).mousedown(function(ev) {
					var ignore = opt('unselectCancel');
					if (ignore) {
						if ($(ev.target).parents(ignore).length) { // could be optimized to stop after first match
							return;
						}
					}
					unselect(ev);
				});
			}
			function select(startDate, endDate, allDay) {
				unselect();
				if (!endDate) {
					endDate = defaultSelectionEnd(startDate, allDay);
				}
				renderSelection(startDate, endDate, allDay);
				reportSelection(startDate, endDate, allDay);
			}
			function unselect(ev) {
				if (selected) {
					selected = false;
					clearSelection();
					trigger('unselect', null, ev);
				}
			}
			function reportSelection(startDate, endDate, allDay, ev) {
				selected = true;
				trigger('select', null, startDate, endDate, allDay, ev);
			}
			function daySelectionMousedown(ev) { // not really a generic manager method, oh well
				var cellToDate = t.cellToDate;
				var getIsCellAllDay = t.getIsCellAllDay;
				var hoverListener = t.getHoverListener();
				var reportDayClick = t.reportDayClick; // this is hacky and sort of weird
				if (ev.which == 1 && opt('selectable')) { // which==1 means left mouse button
					unselect(ev);
					var _mousedownElement = this;
					var dates;
					hoverListener.start(function(cell, origCell) { // TODO: maybe put cellToDate/getIsCellAllDay info in cell
						clearSelection();
						if (cell && getIsCellAllDay(cell)) {
							dates = [ cellToDate(origCell), cellToDate(cell) ].sort(dateCompare);
							renderSelection(dates[0], dates[1], true);
						}else{
							dates = null;
						}
					}, ev);
					$(document).one('mouseup', function(ev) {
						hoverListener.stop();
						if (dates) {
							if (+dates[0] == +dates[1]) {
								reportDayClick(dates[0], true, ev);
							}
							reportSelection(dates[0], dates[1], true, ev);
						}
					});
				}
			}
		}
		;;
		function OverlayManager() {
			var t = this;
			t.renderOverlay = renderOverlay;
			t.clearOverlays = clearOverlays;
			var usedOverlays = [];
			var unusedOverlays = [];
			function renderOverlay(rect, parent) {
				var e = unusedOverlays.shift();
				if (!e) {
					e = $("<div class='fc-cell-overlay' style='position:absolute;z-index:3'/>");
				}
				if (e[0].parentNode != parent[0]) {
					e.appendTo(parent);
				}
				usedOverlays.push(e.css(rect).show());
				return e;
			}
			function clearOverlays() {
				var e;
				while (e = usedOverlays.shift()) {
					unusedOverlays.push(e.hide().unbind());
				}
			}
		}
		;;
		function CoordinateGrid(buildFunc) {
			var t = this;
			var rows;
			var cols;
			t.build = function() {
				rows = [];
				cols = [];
				buildFunc(rows, cols);
			};
			t.cell = function(x, y) {
				var rowCnt = rows.length;
				var colCnt = cols.length;
				var i, r=-1, c=-1;
				for (i=0; i<rowCnt; i++) {
					if (y >= rows[i][0] && y < rows[i][1]) {
						r = i;
						break;
					}
				}
				for (i=0; i<colCnt; i++) {
					if (x >= cols[i][0] && x < cols[i][1]) {
						c = i;
						break;
					}
				}
				return (r>=0 && c>=0) ? { row:r, col:c } : null;
			};
			t.rect = function(row0, col0, row1, col1, originElement) { // row1,col1 is inclusive
				var origin = originElement.offset();
				return {
					top: rows[row0][0] - origin.top,
					left: cols[col0][0] - origin.left,
					width: cols[col1][1] - cols[col0][0],
					height: rows[row1][1] - rows[row0][0]
				};
			};
		}
		;;
		function HoverListener(coordinateGrid) {
			var t = this;
			var bindType;
			var change;
			var firstCell;
			var cell;
			t.start = function(_change, ev, _bindType) {
				change = _change;
				firstCell = cell = null;
				coordinateGrid.build();
				mouse(ev);
				bindType = _bindType || 'mousemove';
				$(document).bind(bindType, mouse);
			};
			function mouse(ev) {
				_fixUIEvent(ev); // see below
				var newCell = coordinateGrid.cell(ev.pageX, ev.pageY);
				if (!newCell != !cell || newCell && (newCell.row != cell.row || newCell.col != cell.col)) {
					if (newCell) {
						if (!firstCell) {
							firstCell = newCell;
						}
						change(newCell, firstCell, newCell.row-firstCell.row, newCell.col-firstCell.col);
					}else{
						change(newCell, firstCell);
					}
					cell = newCell;
				}
			}
			t.stop = function() {
				$(document).unbind(bindType, mouse);
				return cell;
			};
		}
		function _fixUIEvent(event) { // for issue 1168
			if (event.pageX === undefined) {
				event.pageX = event.originalEvent.pageX;
				event.pageY = event.originalEvent.pageY;
			}
		}
		;;
		function HorizontalPositionCache(getElement) {
			var t = this,
				elements = {},
				lefts = {},
				rights = {};
			function e(i) {
				return elements[i] = elements[i] || getElement(i);
			}
			t.left = function(i) {
				return lefts[i] = lefts[i] === undefined ? e(i).position().left : lefts[i];
			};
			t.right = function(i) {
				return rights[i] = rights[i] === undefined ? t.left(i) + e(i).width() : rights[i];
			};
			t.clear = function() {
				elements = {};
				lefts = {};
				rights = {};
			};
		}
		;;
		//农历算法
		function RunGLNL() { 
		    var today = new Date(); 
		    var d = new Array("周日", "周一", "周二", "周三", "周四", "周五", "周六"); 
		    var DDDD = d[today.getDay()]; 
		    DDDD = DDDD + " " + (CnDateofDateStr(today)); //显示农历 
		    DDDD = DDDD + SolarTerm(today); //显示二十四节气 
		    document.write(DDDD); 
		} 
		function DaysNumberofDate(DateGL) { 
		    return parseInt((Date.parse(DateGL) - Date.parse(DateGL.getFullYear() + "/1/1")) / 86400000) + 1; 
		} 
		function CnDateofDate(DateGL) { 
		    var CnData = new Array( 
		        0x16, 0x2a, 0xda, 0x00, 0x83, 0x49, 0xb6, 0x05, 0x0e, 0x64, 0xbb, 0x00, 0x19, 0xb2, 0x5b, 0x00, 
		        0x87, 0x6a, 0x57, 0x04, 0x12, 0x75, 0x2b, 0x00, 0x1d, 0xb6, 0x95, 0x00, 0x8a, 0xad, 0x55, 0x02, 
		        0x15, 0x55, 0xaa, 0x00, 0x82, 0x55, 0x6c, 0x07, 0x0d, 0xc9, 0x76, 0x00, 0x17, 0x64, 0xb7, 0x00, 
		        0x86, 0xe4, 0xae, 0x05, 0x11, 0xea, 0x56, 0x00, 0x1b, 0x6d, 0x2a, 0x00, 0x88, 0x5a, 0xaa, 0x04, 
		        0x14, 0xad, 0x55, 0x00, 0x81, 0xaa, 0xd5, 0x09, 0x0b, 0x52, 0xea, 0x00, 0x16, 0xa9, 0x6d, 0x00, 
		        0x84, 0xa9, 0x5d, 0x06, 0x0f, 0xd4, 0xae, 0x00, 0x1a, 0xea, 0x4d, 0x00, 0x87, 0xba, 0x55, 0x04 
		    ); 
		    var CnMonth = new Array(); 
		    var CnMonthDays = new Array(); 
		    var CnBeginDay; 
		    var LeapMonth; 
		    var Bytes = new Array(); 
		    var I; 
		    var CnMonthData; 
		    var DaysCount; 
		    var CnDaysCount; 
		    var ResultMonth; 
		    var ResultDay; 
		    var yyyy = DateGL.getFullYear(); 
		    var mm = DateGL.getMonth() + 1; 
		    var dd = DateGL.getDate(); 
		    if (yyyy < 100) yyyy += 1900; 
		    if ((yyyy < 1997) || (yyyy > 2020)) { 
		        return 0; 
		    } 
		    Bytes[0] = CnData[(yyyy - 1997) * 4]; 
		    Bytes[1] = CnData[(yyyy - 1997) * 4 + 1]; 
		    Bytes[2] = CnData[(yyyy - 1997) * 4 + 2]; 
		    Bytes[3] = CnData[(yyyy - 1997) * 4 + 3]; 
		    if ((Bytes[0] & 0x80) != 0) { 
		        CnMonth[0] = 12; 
		    } 
		    else { 
		        CnMonth[0] = 11; 
		    } 
		    CnBeginDay = (Bytes[0] & 0x7f); 
		    CnMonthData = Bytes[1]; 
		    CnMonthData = CnMonthData << 8; 
		    CnMonthData = CnMonthData | Bytes[2]; 
		    LeapMonth = Bytes[3]; 
		    for (I = 15; I >= 0; I--) { 
		        CnMonthDays[15 - I] = 29; 
		        if (((1 << I) & CnMonthData) != 0) { 
		            CnMonthDays[15 - I]++; 
		        } 
		        if (CnMonth[15 - I] == LeapMonth) { 
		            CnMonth[15 - I + 1] = -LeapMonth; 
		        } 
		        else { 
		            if (CnMonth[15 - I] < 0) { 
		                CnMonth[15 - I + 1] = -CnMonth[15 - I] + 1; 
		            } 
		            else { 
		                CnMonth[15 - I + 1] = CnMonth[15 - I] + 1; 
		            } 
		            if (CnMonth[15 - I + 1] > 12) { 
		                CnMonth[15 - I + 1] = 1; 
		            } 
		        } 
		    } 
		    DaysCount = DaysNumberofDate(DateGL) - 1; 
		    if (DaysCount <= (CnMonthDays[0] - CnBeginDay)) { 
		        if ((yyyy > 1901) && (CnDateofDate(new Date((yyyy - 1) + "/12/31")) < 0)) { 
		            ResultMonth = -CnMonth[0]; 
		        } 
		        else { 
		            ResultMonth = CnMonth[0]; 
		        } 
		        ResultDay = CnBeginDay + DaysCount; 
		    } 
		    else { 
		        CnDaysCount = CnMonthDays[0] - CnBeginDay; 
		        I = 1; 
		        while ((CnDaysCount < DaysCount) && (CnDaysCount + CnMonthDays[I] < DaysCount)) { 
		            CnDaysCount += CnMonthDays[I]; 
		            I++; 
		        } 
		        ResultMonth = CnMonth[I]; 
		        ResultDay = DaysCount - CnDaysCount; 
		    } 
		    if (ResultMonth > 0) { 
		        return ResultMonth * 100 + ResultDay; 
		    } 
		    else { 
		        return ResultMonth * 100 - ResultDay; 
		    } 
		} 
		function CnYearofDate(DateGL) { 
		    var YYYY = DateGL.getFullYear(); 
		    var MM = DateGL.getMonth() + 1; 
		    var CnMM = parseInt(Math.abs(CnDateofDate(DateGL)) / 100); 
		    if (YYYY < 100) YYYY += 1900; 
		    if (CnMM > MM) YYYY--; 
		    YYYY -= 1864; 
		    return CnEra(YYYY) + "年"; 
		} 
		function CnMonthofDate(DateGL) { 
		    var CnMonthStr = new Array("零", "正", "二", "三", "四", "五", "六", "七", "八", "九", "十", "冬", "腊"); 
		    var Month; 
		    Month = parseInt(CnDateofDate(DateGL) / 100); 
		    if (Month < 0) { 
		        return "闰" + CnMonthStr[-Month] + "月"; 
		    } 
		    else { 
		        return CnMonthStr[Month] + "月"; 
		    } 
		} 
		function CnDayofDate(DateGL) { 
		    var CnDayStr = new Array("零", 
		        "初一", "初二", "初三", "初四", "初五", 
		        "初六", "初七", "初八", "初九", "初十", 
		        "十一", "十二", "十三", "十四", "十五", 
		        "十六", "十七", "十八", "十九", "二十", 
		        "廿一", "廿二", "廿三", "廿四", "廿五", 
		        "廿六", "廿七", "廿八", "廿九", "三十"); 
		    var Day; 
		    Day = (Math.abs(CnDateofDate(DateGL))) % 100; 
	        if (SolarTerm(DateGL) != "") { 
	            return SolarTerm(DateGL); 
	        } else { 
	            return CnDayStr[Day]; 
	        } 
		} 
		function DaysNumberofMonth(DateGL) { 
		    var MM1 = DateGL.getFullYear(); 
		    MM1 < 100 ? MM1 += 1900 : MM1; 
		    var MM2 = MM1; 
		    MM1 += "/" + (DateGL.getMonth() + 1); 
		    MM2 += "/" + (DateGL.getMonth() + 2); 
		    MM1 += "/1"; 
		    MM2 += "/1"; 
		    return parseInt((Date.parse(MM2) - Date.parse(MM1)) / 86400000); 
		} 
		function CnEra(YYYY) { 
		    var Tiangan = new Array("甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"); 
		    var Dizhi = new Array("子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"); 
		    return Tiangan[YYYY % 10] + Dizhi[YYYY % 12]; 
		} 
		function CnDateofDateStr(DateGL) { 
		    if (CnMonthofDate(DateGL) == "零月") return "　请调整您的计算机日期!"; 
		    else return "农历" + CnYearofDate(DateGL) + " " + CnMonthofDate(DateGL) + CnDayofDate(DateGL); 
		} 
		 
		function SolarTerm(DateGL) { 
		    var SolarTermStr = new Array( 
		        "小寒", "大寒", "立春", "雨水", "惊蛰", "春分", 
		        "清明", "谷雨", "立夏", "小满", "芒种", "夏至", 
		        "小暑", "大暑", "立秋", "处暑", "白露", "秋分", 
		        "寒露", "霜降", "立冬", "小雪", "大雪", "冬至"); 
		    var DifferenceInMonth = new Array( 
		        1272060, 1275495, 1281180, 1289445, 1299225, 1310355, 
		        1321560, 1333035, 1342770, 1350855, 1356420, 1359045, 
		        1358580, 1355055, 1348695, 1340040, 1329630, 1318455, 
		        1306935, 1297380, 1286865, 1277730, 1274550, 1271556); 
		    var DifferenceInYear = 31556926; 
		    var BeginTime = new Date(1901 / 1 / 1); 
		    BeginTime.setTime(947120460000); 
		    for (; DateGL.getFullYear() < BeginTime.getFullYear();) { 
		        BeginTime.setTime(BeginTime.getTime() - DifferenceInYear * 1000); 
		    } 
		    for (; DateGL.getFullYear() > BeginTime.getFullYear();) { 
		        BeginTime.setTime(BeginTime.getTime() + DifferenceInYear * 1000); 
		    } 
		    for (var M = 0; DateGL.getMonth() > BeginTime.getMonth(); M++) { 
		        BeginTime.setTime(BeginTime.getTime() + DifferenceInMonth[M] * 1000); 
		    } 
		    if (DateGL.getDate() > BeginTime.getDate()) { 
		        BeginTime.setTime(BeginTime.getTime() + DifferenceInMonth[M] * 1000); 
		        M++; 
		    } 
		    if (DateGL.getDate() > BeginTime.getDate()) { 
		        BeginTime.setTime(BeginTime.getTime() + DifferenceInMonth[M] * 1000); 
		        M == 23 ? M = 0 : M++; 
		    } 
		    var JQ = ""; 
		    if (DateGL.getDate() == BeginTime.getDate()) { 
		        JQ += SolarTermStr[M]; 
		    } 
		    return JQ; 
		} 
		
		})(jQuery);
	})(jQuery);
	layui.link(basePath + '../../lib/layui/lay/modules/fullcalendar/fullcalendar.css');
	exports('fullcalendar', null);
});