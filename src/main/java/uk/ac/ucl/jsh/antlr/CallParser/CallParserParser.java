// Generated from uk/ac/ucl/jsh/antlr/CallParser/CallParser.g4 by ANTLR 4.7.2
package uk.ac.ucl.jsh.antlr.CallParser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CallParserParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, WS=4, NON_KEYWORD=5, SEMI=6, PIPE=7, GT=8, LT=9;
	public static final int
		RULE_compileUnit = 0, RULE_arguments = 1, RULE_argument = 2, RULE_non_quoted = 3, 
		RULE_quoted = 4, RULE_single_quoted = 5, RULE_squote_content = 6, RULE_double_quoted = 7, 
		RULE_dquote_content = 8, RULE_backquoted = 9, RULE_bquote_content = 10, 
		RULE_keyword = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"compileUnit", "arguments", "argument", "non_quoted", "quoted", "single_quoted", 
			"squote_content", "double_quoted", "dquote_content", "backquoted", "bquote_content", 
			"keyword"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'''", "'\"'", "'`'", null, null, "';'", "'|'", "'>'", "'<'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "WS", "NON_KEYWORD", "SEMI", "PIPE", "GT", "LT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "CallParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CallParserParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class CompileUnitContext extends ParserRuleContext {
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(CallParserParser.EOF, 0); }
		public List<TerminalNode> WS() { return getTokens(CallParserParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(CallParserParser.WS, i);
		}
		public CompileUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compileUnit; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallParserVisitor ) return ((CallParserVisitor<? extends T>)visitor).visitCompileUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompileUnitContext compileUnit() throws RecognitionException {
		CompileUnitContext _localctx = new CompileUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_compileUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WS) {
				{
				{
				setState(24);
				match(WS);
				}
				}
				setState(29);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(30);
			arguments(0);
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WS) {
				{
				{
				setState(31);
				match(WS);
				}
				}
				setState(36);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(37);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentsContext extends ParserRuleContext {
		public ArgumentsContext cmd;
		public ArgumentsContext left_arguments;
		public Token io_operator;
		public ArgumentsContext file;
		public ArgumentContext argument() {
			return getRuleContext(ArgumentContext.class,0);
		}
		public List<ArgumentsContext> arguments() {
			return getRuleContexts(ArgumentsContext.class);
		}
		public ArgumentsContext arguments(int i) {
			return getRuleContext(ArgumentsContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(CallParserParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(CallParserParser.WS, i);
		}
		public TerminalNode GT() { return getToken(CallParserParser.GT, 0); }
		public TerminalNode LT() { return getToken(CallParserParser.LT, 0); }
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallParserVisitor ) return ((CallParserVisitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		return arguments(0);
	}

	private ArgumentsContext arguments(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, _parentState);
		ArgumentsContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_arguments, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(40);
				argument();
				}
				break;
			case 2:
				{
				setState(41);
				argument();
				setState(43); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(42);
					match(WS);
					}
					}
					setState(45); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WS );
				setState(47);
				((ArgumentsContext)_localctx).left_arguments = arguments(5);
				}
				break;
			case 3:
				{
				setState(49);
				((ArgumentsContext)_localctx).io_operator = match(GT);
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==WS) {
					{
					{
					setState(50);
					match(WS);
					}
					}
					setState(55);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(56);
				((ArgumentsContext)_localctx).file = arguments(0);
				setState(58); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(57);
					match(WS);
					}
					}
					setState(60); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WS );
				setState(62);
				((ArgumentsContext)_localctx).cmd = arguments(3);
				}
				break;
			case 4:
				{
				setState(64);
				((ArgumentsContext)_localctx).io_operator = match(LT);
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==WS) {
					{
					{
					setState(65);
					match(WS);
					}
					}
					setState(70);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(71);
				((ArgumentsContext)_localctx).file = arguments(0);
				setState(73); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(72);
					match(WS);
					}
					}
					setState(75); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WS );
				setState(77);
				((ArgumentsContext)_localctx).cmd = arguments(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(113);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(111);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new ArgumentsContext(_parentctx, _parentState);
						_localctx.cmd = _prevctx;
						_localctx.cmd = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_arguments);
						setState(81);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(85);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(82);
							match(WS);
							}
							}
							setState(87);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(88);
						((ArgumentsContext)_localctx).io_operator = match(GT);
						setState(92);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(89);
							match(WS);
							}
							}
							setState(94);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(95);
						((ArgumentsContext)_localctx).file = arguments(5);
						}
						break;
					case 2:
						{
						_localctx = new ArgumentsContext(_parentctx, _parentState);
						_localctx.cmd = _prevctx;
						_localctx.cmd = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_arguments);
						setState(96);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(100);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(97);
							match(WS);
							}
							}
							setState(102);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(103);
						((ArgumentsContext)_localctx).io_operator = match(LT);
						setState(107);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(104);
							match(WS);
							}
							}
							setState(109);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(110);
						((ArgumentsContext)_localctx).file = arguments(3);
						}
						break;
					}
					} 
				}
				setState(115);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ArgumentContext extends ParserRuleContext {
		public Non_quotedContext non_quote;
		public Non_quotedContext non_quoted() {
			return getRuleContext(Non_quotedContext.class,0);
		}
		public ArgumentContext argument() {
			return getRuleContext(ArgumentContext.class,0);
		}
		public QuotedContext quoted() {
			return getRuleContext(QuotedContext.class,0);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallParserVisitor ) return ((CallParserVisitor<? extends T>)visitor).visitArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_argument);
		try {
			setState(124);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NON_KEYWORD:
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				((ArgumentContext)_localctx).non_quote = non_quoted();
				setState(118);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(117);
					argument();
					}
					break;
				}
				}
				break;
			case T__0:
			case T__1:
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(120);
				quoted();
				setState(122);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(121);
					argument();
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Non_quotedContext extends ParserRuleContext {
		public List<TerminalNode> NON_KEYWORD() { return getTokens(CallParserParser.NON_KEYWORD); }
		public TerminalNode NON_KEYWORD(int i) {
			return getToken(CallParserParser.NON_KEYWORD, i);
		}
		public Non_quotedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_non_quoted; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallParserVisitor ) return ((CallParserVisitor<? extends T>)visitor).visitNon_quoted(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Non_quotedContext non_quoted() throws RecognitionException {
		Non_quotedContext _localctx = new Non_quotedContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_non_quoted);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(127); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(126);
					match(NON_KEYWORD);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(129); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QuotedContext extends ParserRuleContext {
		public Single_quotedContext single_quoted() {
			return getRuleContext(Single_quotedContext.class,0);
		}
		public Double_quotedContext double_quoted() {
			return getRuleContext(Double_quotedContext.class,0);
		}
		public BackquotedContext backquoted() {
			return getRuleContext(BackquotedContext.class,0);
		}
		public QuotedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quoted; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallParserVisitor ) return ((CallParserVisitor<? extends T>)visitor).visitQuoted(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuotedContext quoted() throws RecognitionException {
		QuotedContext _localctx = new QuotedContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_quoted);
		try {
			setState(134);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(131);
				single_quoted();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(132);
				double_quoted();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 3);
				{
				setState(133);
				backquoted();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Single_quotedContext extends ParserRuleContext {
		public Squote_contentContext squote_content() {
			return getRuleContext(Squote_contentContext.class,0);
		}
		public Single_quotedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_single_quoted; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallParserVisitor ) return ((CallParserVisitor<? extends T>)visitor).visitSingle_quoted(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Single_quotedContext single_quoted() throws RecognitionException {
		Single_quotedContext _localctx = new Single_quotedContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_single_quoted);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(T__0);
			setState(137);
			squote_content();
			setState(138);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Squote_contentContext extends ParserRuleContext {
		public List<TerminalNode> NON_KEYWORD() { return getTokens(CallParserParser.NON_KEYWORD); }
		public TerminalNode NON_KEYWORD(int i) {
			return getToken(CallParserParser.NON_KEYWORD, i);
		}
		public List<KeywordContext> keyword() {
			return getRuleContexts(KeywordContext.class);
		}
		public KeywordContext keyword(int i) {
			return getRuleContext(KeywordContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(CallParserParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(CallParserParser.WS, i);
		}
		public Squote_contentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_squote_content; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallParserVisitor ) return ((CallParserVisitor<? extends T>)visitor).visitSquote_content(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Squote_contentContext squote_content() throws RecognitionException {
		Squote_contentContext _localctx = new Squote_contentContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_squote_content);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << WS) | (1L << NON_KEYWORD) | (1L << SEMI) | (1L << PIPE) | (1L << GT) | (1L << LT))) != 0)) {
				{
				setState(145);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NON_KEYWORD:
					{
					setState(140);
					match(NON_KEYWORD);
					}
					break;
				case SEMI:
				case PIPE:
				case GT:
				case LT:
					{
					setState(141);
					keyword();
					}
					break;
				case WS:
					{
					setState(142);
					match(WS);
					}
					break;
				case T__1:
					{
					setState(143);
					match(T__1);
					}
					break;
				case T__2:
					{
					setState(144);
					match(T__2);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Double_quotedContext extends ParserRuleContext {
		public Dquote_contentContext dquote_content() {
			return getRuleContext(Dquote_contentContext.class,0);
		}
		public Double_quotedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double_quoted; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallParserVisitor ) return ((CallParserVisitor<? extends T>)visitor).visitDouble_quoted(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Double_quotedContext double_quoted() throws RecognitionException {
		Double_quotedContext _localctx = new Double_quotedContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_double_quoted);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(T__1);
			setState(151);
			dquote_content();
			setState(152);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Dquote_contentContext extends ParserRuleContext {
		public Token content;
		public Dquote_contentContext dquote_content() {
			return getRuleContext(Dquote_contentContext.class,0);
		}
		public TerminalNode NON_KEYWORD() { return getToken(CallParserParser.NON_KEYWORD, 0); }
		public TerminalNode SEMI() { return getToken(CallParserParser.SEMI, 0); }
		public TerminalNode PIPE() { return getToken(CallParserParser.PIPE, 0); }
		public TerminalNode GT() { return getToken(CallParserParser.GT, 0); }
		public TerminalNode LT() { return getToken(CallParserParser.LT, 0); }
		public TerminalNode WS() { return getToken(CallParserParser.WS, 0); }
		public BackquotedContext backquoted() {
			return getRuleContext(BackquotedContext.class,0);
		}
		public Dquote_contentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dquote_content; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallParserVisitor ) return ((CallParserVisitor<? extends T>)visitor).visitDquote_content(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Dquote_contentContext dquote_content() throws RecognitionException {
		Dquote_contentContext _localctx = new Dquote_contentContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_dquote_content);
		int _la;
		try {
			setState(160);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case WS:
			case NON_KEYWORD:
			case SEMI:
			case PIPE:
			case GT:
			case LT:
				enterOuterAlt(_localctx, 1);
				{
				setState(154);
				((Dquote_contentContext)_localctx).content = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << WS) | (1L << NON_KEYWORD) | (1L << SEMI) | (1L << PIPE) | (1L << GT) | (1L << LT))) != 0)) ) {
					((Dquote_contentContext)_localctx).content = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(155);
				dquote_content();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(156);
				backquoted();
				setState(157);
				dquote_content();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BackquotedContext extends ParserRuleContext {
		public Bquote_contentContext content;
		public Bquote_contentContext bquote_content() {
			return getRuleContext(Bquote_contentContext.class,0);
		}
		public BackquotedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_backquoted; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallParserVisitor ) return ((CallParserVisitor<? extends T>)visitor).visitBackquoted(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BackquotedContext backquoted() throws RecognitionException {
		BackquotedContext _localctx = new BackquotedContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_backquoted);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(T__2);
			setState(163);
			((BackquotedContext)_localctx).content = bquote_content();
			setState(164);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Bquote_contentContext extends ParserRuleContext {
		public List<TerminalNode> NON_KEYWORD() { return getTokens(CallParserParser.NON_KEYWORD); }
		public TerminalNode NON_KEYWORD(int i) {
			return getToken(CallParserParser.NON_KEYWORD, i);
		}
		public List<KeywordContext> keyword() {
			return getRuleContexts(KeywordContext.class);
		}
		public KeywordContext keyword(int i) {
			return getRuleContext(KeywordContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(CallParserParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(CallParserParser.WS, i);
		}
		public Bquote_contentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bquote_content; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallParserVisitor ) return ((CallParserVisitor<? extends T>)visitor).visitBquote_content(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bquote_contentContext bquote_content() throws RecognitionException {
		Bquote_contentContext _localctx = new Bquote_contentContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_bquote_content);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << WS) | (1L << NON_KEYWORD) | (1L << SEMI) | (1L << PIPE) | (1L << GT) | (1L << LT))) != 0)) {
				{
				setState(171);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NON_KEYWORD:
					{
					setState(166);
					match(NON_KEYWORD);
					}
					break;
				case SEMI:
				case PIPE:
				case GT:
				case LT:
					{
					setState(167);
					keyword();
					}
					break;
				case WS:
					{
					setState(168);
					match(WS);
					}
					break;
				case T__1:
					{
					setState(169);
					match(T__1);
					}
					break;
				case T__0:
					{
					setState(170);
					match(T__0);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeywordContext extends ParserRuleContext {
		public TerminalNode SEMI() { return getToken(CallParserParser.SEMI, 0); }
		public TerminalNode PIPE() { return getToken(CallParserParser.PIPE, 0); }
		public TerminalNode GT() { return getToken(CallParserParser.GT, 0); }
		public TerminalNode LT() { return getToken(CallParserParser.LT, 0); }
		public KeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyword; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallParserVisitor ) return ((CallParserVisitor<? extends T>)visitor).visitKeyword(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeywordContext keyword() throws RecognitionException {
		KeywordContext _localctx = new KeywordContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SEMI) | (1L << PIPE) | (1L << GT) | (1L << LT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return arguments_sempred((ArgumentsContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean arguments_sempred(ArgumentsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\13\u00b5\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\3\2\7\2\34\n\2\f\2\16\2\37\13\2\3\2\3\2\7\2#\n\2"+
		"\f\2\16\2&\13\2\3\2\3\2\3\3\3\3\3\3\3\3\6\3.\n\3\r\3\16\3/\3\3\3\3\3\3"+
		"\3\3\7\3\66\n\3\f\3\16\39\13\3\3\3\3\3\6\3=\n\3\r\3\16\3>\3\3\3\3\3\3"+
		"\3\3\7\3E\n\3\f\3\16\3H\13\3\3\3\3\3\6\3L\n\3\r\3\16\3M\3\3\3\3\5\3R\n"+
		"\3\3\3\3\3\7\3V\n\3\f\3\16\3Y\13\3\3\3\3\3\7\3]\n\3\f\3\16\3`\13\3\3\3"+
		"\3\3\3\3\7\3e\n\3\f\3\16\3h\13\3\3\3\3\3\7\3l\n\3\f\3\16\3o\13\3\3\3\7"+
		"\3r\n\3\f\3\16\3u\13\3\3\4\3\4\5\4y\n\4\3\4\3\4\5\4}\n\4\5\4\177\n\4\3"+
		"\5\6\5\u0082\n\5\r\5\16\5\u0083\3\6\3\6\3\6\5\6\u0089\n\6\3\7\3\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\b\7\b\u0094\n\b\f\b\16\b\u0097\13\b\3\t\3\t\3\t"+
		"\3\t\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00a3\n\n\3\13\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\f\3\f\7\f\u00ae\n\f\f\f\16\f\u00b1\13\f\3\r\3\r\3\r\2\3\4\16\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\2\4\4\2\3\3\6\13\3\2\b\13\2\u00ca\2\35\3"+
		"\2\2\2\4Q\3\2\2\2\6~\3\2\2\2\b\u0081\3\2\2\2\n\u0088\3\2\2\2\f\u008a\3"+
		"\2\2\2\16\u0095\3\2\2\2\20\u0098\3\2\2\2\22\u00a2\3\2\2\2\24\u00a4\3\2"+
		"\2\2\26\u00af\3\2\2\2\30\u00b2\3\2\2\2\32\34\7\6\2\2\33\32\3\2\2\2\34"+
		"\37\3\2\2\2\35\33\3\2\2\2\35\36\3\2\2\2\36 \3\2\2\2\37\35\3\2\2\2 $\5"+
		"\4\3\2!#\7\6\2\2\"!\3\2\2\2#&\3\2\2\2$\"\3\2\2\2$%\3\2\2\2%\'\3\2\2\2"+
		"&$\3\2\2\2\'(\7\2\2\3(\3\3\2\2\2)*\b\3\1\2*R\5\6\4\2+-\5\6\4\2,.\7\6\2"+
		"\2-,\3\2\2\2./\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\61\3\2\2\2\61\62\5\4\3"+
		"\7\62R\3\2\2\2\63\67\7\n\2\2\64\66\7\6\2\2\65\64\3\2\2\2\669\3\2\2\2\67"+
		"\65\3\2\2\2\678\3\2\2\28:\3\2\2\29\67\3\2\2\2:<\5\4\3\2;=\7\6\2\2<;\3"+
		"\2\2\2=>\3\2\2\2><\3\2\2\2>?\3\2\2\2?@\3\2\2\2@A\5\4\3\5AR\3\2\2\2BF\7"+
		"\13\2\2CE\7\6\2\2DC\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2GI\3\2\2\2HF"+
		"\3\2\2\2IK\5\4\3\2JL\7\6\2\2KJ\3\2\2\2LM\3\2\2\2MK\3\2\2\2MN\3\2\2\2N"+
		"O\3\2\2\2OP\5\4\3\3PR\3\2\2\2Q)\3\2\2\2Q+\3\2\2\2Q\63\3\2\2\2QB\3\2\2"+
		"\2Rs\3\2\2\2SW\f\6\2\2TV\7\6\2\2UT\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3\2\2"+
		"\2XZ\3\2\2\2YW\3\2\2\2Z^\7\n\2\2[]\7\6\2\2\\[\3\2\2\2]`\3\2\2\2^\\\3\2"+
		"\2\2^_\3\2\2\2_a\3\2\2\2`^\3\2\2\2ar\5\4\3\7bf\f\4\2\2ce\7\6\2\2dc\3\2"+
		"\2\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2gi\3\2\2\2hf\3\2\2\2im\7\13\2\2jl\7"+
		"\6\2\2kj\3\2\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2\2\2np\3\2\2\2om\3\2\2\2pr\5"+
		"\4\3\5qS\3\2\2\2qb\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2t\5\3\2\2\2us"+
		"\3\2\2\2vx\5\b\5\2wy\5\6\4\2xw\3\2\2\2xy\3\2\2\2y\177\3\2\2\2z|\5\n\6"+
		"\2{}\5\6\4\2|{\3\2\2\2|}\3\2\2\2}\177\3\2\2\2~v\3\2\2\2~z\3\2\2\2\177"+
		"\7\3\2\2\2\u0080\u0082\7\7\2\2\u0081\u0080\3\2\2\2\u0082\u0083\3\2\2\2"+
		"\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\t\3\2\2\2\u0085\u0089\5"+
		"\f\7\2\u0086\u0089\5\20\t\2\u0087\u0089\5\24\13\2\u0088\u0085\3\2\2\2"+
		"\u0088\u0086\3\2\2\2\u0088\u0087\3\2\2\2\u0089\13\3\2\2\2\u008a\u008b"+
		"\7\3\2\2\u008b\u008c\5\16\b\2\u008c\u008d\7\3\2\2\u008d\r\3\2\2\2\u008e"+
		"\u0094\7\7\2\2\u008f\u0094\5\30\r\2\u0090\u0094\7\6\2\2\u0091\u0094\7"+
		"\4\2\2\u0092\u0094\7\5\2\2\u0093\u008e\3\2\2\2\u0093\u008f\3\2\2\2\u0093"+
		"\u0090\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0092\3\2\2\2\u0094\u0097\3\2"+
		"\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\17\3\2\2\2\u0097\u0095"+
		"\3\2\2\2\u0098\u0099\7\4\2\2\u0099\u009a\5\22\n\2\u009a\u009b\7\4\2\2"+
		"\u009b\21\3\2\2\2\u009c\u009d\t\2\2\2\u009d\u00a3\5\22\n\2\u009e\u009f"+
		"\5\24\13\2\u009f\u00a0\5\22\n\2\u00a0\u00a3\3\2\2\2\u00a1\u00a3\3\2\2"+
		"\2\u00a2\u009c\3\2\2\2\u00a2\u009e\3\2\2\2\u00a2\u00a1\3\2\2\2\u00a3\23"+
		"\3\2\2\2\u00a4\u00a5\7\5\2\2\u00a5\u00a6\5\26\f\2\u00a6\u00a7\7\5\2\2"+
		"\u00a7\25\3\2\2\2\u00a8\u00ae\7\7\2\2\u00a9\u00ae\5\30\r\2\u00aa\u00ae"+
		"\7\6\2\2\u00ab\u00ae\7\4\2\2\u00ac\u00ae\7\3\2\2\u00ad\u00a8\3\2\2\2\u00ad"+
		"\u00a9\3\2\2\2\u00ad\u00aa\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ac\3\2"+
		"\2\2\u00ae\u00b1\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0"+
		"\27\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b3\t\3\2\2\u00b3\31\3\2\2\2\32"+
		"\35$/\67>FMQW^fmqsx|~\u0083\u0088\u0093\u0095\u00a2\u00ad\u00af";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}