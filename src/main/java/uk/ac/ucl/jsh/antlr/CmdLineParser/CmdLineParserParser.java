// Generated from uk/ac/ucl/jsh/antlr/CmdLineParser/CmdLineParser.g4 by ANTLR 4.7.2
package uk.ac.ucl.jsh.antlr.CmdLineParser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CmdLineParserParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, WS=4, NON_KEYWORD=5, SEMI=6, PIPE=7, GT=8, LT=9;
	public static final int
		RULE_compileUnit = 0, RULE_command = 1, RULE_pipe = 2, RULE_seq = 3, RULE_call = 4, 
		RULE_single_quoted = 5, RULE_squote_content = 6, RULE_double_quoted = 7, 
		RULE_dquote_content = 8, RULE_backquoted = 9, RULE_bquote_content = 10, 
		RULE_keyword = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"compileUnit", "command", "pipe", "seq", "call", "single_quoted", "squote_content", 
			"double_quoted", "dquote_content", "backquoted", "bquote_content", "keyword"
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
	public String getGrammarFileName() { return "CmdLineParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CmdLineParserParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class CompileUnitContext extends ParserRuleContext {
		public SeqContext seq() {
			return getRuleContext(SeqContext.class,0);
		}
		public TerminalNode EOF() { return getToken(CmdLineParserParser.EOF, 0); }
		public CommandContext command() {
			return getRuleContext(CommandContext.class,0);
		}
		public CompileUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compileUnit; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmdLineParserVisitor ) return ((CmdLineParserVisitor<? extends T>)visitor).visitCompileUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompileUnitContext compileUnit() throws RecognitionException {
		CompileUnitContext _localctx = new CompileUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_compileUnit);
		try {
			setState(30);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(24);
				seq(0);
				setState(25);
				match(EOF);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				command();
				setState(28);
				match(EOF);
				}
				break;
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

	public static class CommandContext extends ParserRuleContext {
		public PipeContext pipe() {
			return getRuleContext(PipeContext.class,0);
		}
		public CallContext call() {
			return getRuleContext(CallContext.class,0);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmdLineParserVisitor ) return ((CmdLineParserVisitor<? extends T>)visitor).visitCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_command);
		try {
			setState(34);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(32);
				pipe(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(33);
				call();
				}
				break;
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

	public static class PipeContext extends ParserRuleContext {
		public PipeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pipe; }
	 
		public PipeContext() { }
		public void copyFrom(PipeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PipeBaseContext extends PipeContext {
		public CallContext call1;
		public CallContext call2;
		public TerminalNode PIPE() { return getToken(CmdLineParserParser.PIPE, 0); }
		public List<CallContext> call() {
			return getRuleContexts(CallContext.class);
		}
		public CallContext call(int i) {
			return getRuleContext(CallContext.class,i);
		}
		public PipeBaseContext(PipeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmdLineParserVisitor ) return ((CmdLineParserVisitor<? extends T>)visitor).visitPipeBase(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PipeRecursiveContext extends PipeContext {
		public PipeContext pipe() {
			return getRuleContext(PipeContext.class,0);
		}
		public TerminalNode PIPE() { return getToken(CmdLineParserParser.PIPE, 0); }
		public CallContext call() {
			return getRuleContext(CallContext.class,0);
		}
		public PipeRecursiveContext(PipeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmdLineParserVisitor ) return ((CmdLineParserVisitor<? extends T>)visitor).visitPipeRecursive(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PipeContext pipe() throws RecognitionException {
		return pipe(0);
	}

	private PipeContext pipe(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PipeContext _localctx = new PipeContext(_ctx, _parentState);
		PipeContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_pipe, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new PipeBaseContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(37);
			((PipeBaseContext)_localctx).call1 = call();
			setState(38);
			match(PIPE);
			setState(39);
			((PipeBaseContext)_localctx).call2 = call();
			}
			_ctx.stop = _input.LT(-1);
			setState(46);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new PipeRecursiveContext(new PipeContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_pipe);
					setState(41);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(42);
					match(PIPE);
					setState(43);
					call();
					}
					} 
				}
				setState(48);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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

	public static class SeqContext extends ParserRuleContext {
		public SeqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_seq; }
	 
		public SeqContext() { }
		public void copyFrom(SeqContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SeqRecursiveContext extends SeqContext {
		public SeqContext seq() {
			return getRuleContext(SeqContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(CmdLineParserParser.SEMI, 0); }
		public CommandContext command() {
			return getRuleContext(CommandContext.class,0);
		}
		public SeqRecursiveContext(SeqContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmdLineParserVisitor ) return ((CmdLineParserVisitor<? extends T>)visitor).visitSeqRecursive(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SeqBaseContext extends SeqContext {
		public CommandContext cmd1;
		public CommandContext cmd2;
		public TerminalNode SEMI() { return getToken(CmdLineParserParser.SEMI, 0); }
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public SeqBaseContext(SeqContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmdLineParserVisitor ) return ((CmdLineParserVisitor<? extends T>)visitor).visitSeqBase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SeqContext seq() throws RecognitionException {
		return seq(0);
	}

	private SeqContext seq(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SeqContext _localctx = new SeqContext(_ctx, _parentState);
		SeqContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_seq, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new SeqBaseContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(50);
			((SeqBaseContext)_localctx).cmd1 = command();
			setState(51);
			match(SEMI);
			setState(52);
			((SeqBaseContext)_localctx).cmd2 = command();
			}
			_ctx.stop = _input.LT(-1);
			setState(59);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new SeqRecursiveContext(new SeqContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_seq);
					setState(54);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(55);
					match(SEMI);
					setState(56);
					command();
					}
					} 
				}
				setState(61);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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

	public static class CallContext extends ParserRuleContext {
		public List<TerminalNode> NON_KEYWORD() { return getTokens(CmdLineParserParser.NON_KEYWORD); }
		public TerminalNode NON_KEYWORD(int i) {
			return getToken(CmdLineParserParser.NON_KEYWORD, i);
		}
		public List<TerminalNode> LT() { return getTokens(CmdLineParserParser.LT); }
		public TerminalNode LT(int i) {
			return getToken(CmdLineParserParser.LT, i);
		}
		public List<TerminalNode> GT() { return getTokens(CmdLineParserParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(CmdLineParserParser.GT, i);
		}
		public List<Single_quotedContext> single_quoted() {
			return getRuleContexts(Single_quotedContext.class);
		}
		public Single_quotedContext single_quoted(int i) {
			return getRuleContext(Single_quotedContext.class,i);
		}
		public List<Double_quotedContext> double_quoted() {
			return getRuleContexts(Double_quotedContext.class);
		}
		public Double_quotedContext double_quoted(int i) {
			return getRuleContext(Double_quotedContext.class,i);
		}
		public List<BackquotedContext> backquoted() {
			return getRuleContexts(BackquotedContext.class);
		}
		public BackquotedContext backquoted(int i) {
			return getRuleContext(BackquotedContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(CmdLineParserParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(CmdLineParserParser.WS, i);
		}
		public CallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmdLineParserVisitor ) return ((CmdLineParserVisitor<? extends T>)visitor).visitCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallContext call() throws RecognitionException {
		CallContext _localctx = new CallContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_call);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(69); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(69);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case NON_KEYWORD:
						{
						setState(62);
						match(NON_KEYWORD);
						}
						break;
					case LT:
						{
						setState(63);
						match(LT);
						}
						break;
					case GT:
						{
						setState(64);
						match(GT);
						}
						break;
					case T__0:
						{
						setState(65);
						single_quoted();
						}
						break;
					case T__1:
						{
						setState(66);
						double_quoted();
						}
						break;
					case T__2:
						{
						setState(67);
						backquoted();
						}
						break;
					case WS:
						{
						setState(68);
						match(WS);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(71); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
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
			if ( visitor instanceof CmdLineParserVisitor ) return ((CmdLineParserVisitor<? extends T>)visitor).visitSingle_quoted(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Single_quotedContext single_quoted() throws RecognitionException {
		Single_quotedContext _localctx = new Single_quotedContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_single_quoted);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			match(T__0);
			setState(74);
			squote_content();
			setState(75);
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
		public List<TerminalNode> NON_KEYWORD() { return getTokens(CmdLineParserParser.NON_KEYWORD); }
		public TerminalNode NON_KEYWORD(int i) {
			return getToken(CmdLineParserParser.NON_KEYWORD, i);
		}
		public List<KeywordContext> keyword() {
			return getRuleContexts(KeywordContext.class);
		}
		public KeywordContext keyword(int i) {
			return getRuleContext(KeywordContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(CmdLineParserParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(CmdLineParserParser.WS, i);
		}
		public Squote_contentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_squote_content; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmdLineParserVisitor ) return ((CmdLineParserVisitor<? extends T>)visitor).visitSquote_content(this);
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
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << WS) | (1L << NON_KEYWORD) | (1L << SEMI) | (1L << PIPE) | (1L << GT) | (1L << LT))) != 0)) {
				{
				setState(82);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NON_KEYWORD:
					{
					setState(77);
					match(NON_KEYWORD);
					}
					break;
				case SEMI:
				case PIPE:
				case GT:
				case LT:
					{
					setState(78);
					keyword();
					}
					break;
				case WS:
					{
					setState(79);
					match(WS);
					}
					break;
				case T__1:
					{
					setState(80);
					match(T__1);
					}
					break;
				case T__2:
					{
					setState(81);
					match(T__2);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(86);
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
			if ( visitor instanceof CmdLineParserVisitor ) return ((CmdLineParserVisitor<? extends T>)visitor).visitDouble_quoted(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Double_quotedContext double_quoted() throws RecognitionException {
		Double_quotedContext _localctx = new Double_quotedContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_double_quoted);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(T__1);
			setState(88);
			dquote_content();
			setState(89);
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
		public List<TerminalNode> NON_KEYWORD() { return getTokens(CmdLineParserParser.NON_KEYWORD); }
		public TerminalNode NON_KEYWORD(int i) {
			return getToken(CmdLineParserParser.NON_KEYWORD, i);
		}
		public List<KeywordContext> keyword() {
			return getRuleContexts(KeywordContext.class);
		}
		public KeywordContext keyword(int i) {
			return getRuleContext(KeywordContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(CmdLineParserParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(CmdLineParserParser.WS, i);
		}
		public List<BackquotedContext> backquoted() {
			return getRuleContexts(BackquotedContext.class);
		}
		public BackquotedContext backquoted(int i) {
			return getRuleContext(BackquotedContext.class,i);
		}
		public Dquote_contentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dquote_content; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmdLineParserVisitor ) return ((CmdLineParserVisitor<? extends T>)visitor).visitDquote_content(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Dquote_contentContext dquote_content() throws RecognitionException {
		Dquote_contentContext _localctx = new Dquote_contentContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_dquote_content);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << WS) | (1L << NON_KEYWORD) | (1L << SEMI) | (1L << PIPE) | (1L << GT) | (1L << LT))) != 0)) {
				{
				setState(96);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NON_KEYWORD:
					{
					setState(91);
					match(NON_KEYWORD);
					}
					break;
				case SEMI:
				case PIPE:
				case GT:
				case LT:
					{
					setState(92);
					keyword();
					}
					break;
				case WS:
					{
					setState(93);
					match(WS);
					}
					break;
				case T__0:
					{
					setState(94);
					match(T__0);
					}
					break;
				case T__2:
					{
					setState(95);
					backquoted();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(100);
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
			if ( visitor instanceof CmdLineParserVisitor ) return ((CmdLineParserVisitor<? extends T>)visitor).visitBackquoted(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BackquotedContext backquoted() throws RecognitionException {
		BackquotedContext _localctx = new BackquotedContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_backquoted);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(T__2);
			setState(102);
			((BackquotedContext)_localctx).content = bquote_content();
			setState(103);
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
		public List<TerminalNode> NON_KEYWORD() { return getTokens(CmdLineParserParser.NON_KEYWORD); }
		public TerminalNode NON_KEYWORD(int i) {
			return getToken(CmdLineParserParser.NON_KEYWORD, i);
		}
		public List<KeywordContext> keyword() {
			return getRuleContexts(KeywordContext.class);
		}
		public KeywordContext keyword(int i) {
			return getRuleContext(KeywordContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(CmdLineParserParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(CmdLineParserParser.WS, i);
		}
		public Bquote_contentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bquote_content; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmdLineParserVisitor ) return ((CmdLineParserVisitor<? extends T>)visitor).visitBquote_content(this);
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
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << WS) | (1L << NON_KEYWORD) | (1L << SEMI) | (1L << PIPE) | (1L << GT) | (1L << LT))) != 0)) {
				{
				setState(110);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NON_KEYWORD:
					{
					setState(105);
					match(NON_KEYWORD);
					}
					break;
				case SEMI:
				case PIPE:
				case GT:
				case LT:
					{
					setState(106);
					keyword();
					}
					break;
				case WS:
					{
					setState(107);
					match(WS);
					}
					break;
				case T__1:
					{
					setState(108);
					match(T__1);
					}
					break;
				case T__0:
					{
					setState(109);
					match(T__0);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(114);
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
		public TerminalNode SEMI() { return getToken(CmdLineParserParser.SEMI, 0); }
		public TerminalNode PIPE() { return getToken(CmdLineParserParser.PIPE, 0); }
		public TerminalNode GT() { return getToken(CmdLineParserParser.GT, 0); }
		public TerminalNode LT() { return getToken(CmdLineParserParser.LT, 0); }
		public KeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyword; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CmdLineParserVisitor ) return ((CmdLineParserVisitor<? extends T>)visitor).visitKeyword(this);
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
			setState(115);
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
		case 2:
			return pipe_sempred((PipeContext)_localctx, predIndex);
		case 3:
			return seq_sempred((SeqContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean pipe_sempred(PipeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean seq_sempred(SeqContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\13x\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\2\3\2\5\2!\n\2\3\3\3\3\5\3%\n\3\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4/\n\4\f\4\16\4\62\13\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\7\5<\n\5\f\5\16\5?\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\6\6"+
		"H\n\6\r\6\16\6I\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\7\bU\n\b\f\b\16\b"+
		"X\13\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\7\nc\n\n\f\n\16\nf\13\n\3\13"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\7\fq\n\f\f\f\16\ft\13\f\3\r\3\r\3"+
		"\r\2\4\6\b\16\2\4\6\b\n\f\16\20\22\24\26\30\2\3\3\2\b\13\2\u0085\2 \3"+
		"\2\2\2\4$\3\2\2\2\6&\3\2\2\2\b\63\3\2\2\2\nG\3\2\2\2\fK\3\2\2\2\16V\3"+
		"\2\2\2\20Y\3\2\2\2\22d\3\2\2\2\24g\3\2\2\2\26r\3\2\2\2\30u\3\2\2\2\32"+
		"\33\5\b\5\2\33\34\7\2\2\3\34!\3\2\2\2\35\36\5\4\3\2\36\37\7\2\2\3\37!"+
		"\3\2\2\2 \32\3\2\2\2 \35\3\2\2\2!\3\3\2\2\2\"%\5\6\4\2#%\5\n\6\2$\"\3"+
		"\2\2\2$#\3\2\2\2%\5\3\2\2\2&\'\b\4\1\2\'(\5\n\6\2()\7\t\2\2)*\5\n\6\2"+
		"*\60\3\2\2\2+,\f\3\2\2,-\7\t\2\2-/\5\n\6\2.+\3\2\2\2/\62\3\2\2\2\60.\3"+
		"\2\2\2\60\61\3\2\2\2\61\7\3\2\2\2\62\60\3\2\2\2\63\64\b\5\1\2\64\65\5"+
		"\4\3\2\65\66\7\b\2\2\66\67\5\4\3\2\67=\3\2\2\289\f\4\2\29:\7\b\2\2:<\5"+
		"\4\3\2;8\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>\t\3\2\2\2?=\3\2\2\2@H"+
		"\7\7\2\2AH\7\13\2\2BH\7\n\2\2CH\5\f\7\2DH\5\20\t\2EH\5\24\13\2FH\7\6\2"+
		"\2G@\3\2\2\2GA\3\2\2\2GB\3\2\2\2GC\3\2\2\2GD\3\2\2\2GE\3\2\2\2GF\3\2\2"+
		"\2HI\3\2\2\2IG\3\2\2\2IJ\3\2\2\2J\13\3\2\2\2KL\7\3\2\2LM\5\16\b\2MN\7"+
		"\3\2\2N\r\3\2\2\2OU\7\7\2\2PU\5\30\r\2QU\7\6\2\2RU\7\4\2\2SU\7\5\2\2T"+
		"O\3\2\2\2TP\3\2\2\2TQ\3\2\2\2TR\3\2\2\2TS\3\2\2\2UX\3\2\2\2VT\3\2\2\2"+
		"VW\3\2\2\2W\17\3\2\2\2XV\3\2\2\2YZ\7\4\2\2Z[\5\22\n\2[\\\7\4\2\2\\\21"+
		"\3\2\2\2]c\7\7\2\2^c\5\30\r\2_c\7\6\2\2`c\7\3\2\2ac\5\24\13\2b]\3\2\2"+
		"\2b^\3\2\2\2b_\3\2\2\2b`\3\2\2\2ba\3\2\2\2cf\3\2\2\2db\3\2\2\2de\3\2\2"+
		"\2e\23\3\2\2\2fd\3\2\2\2gh\7\5\2\2hi\5\26\f\2ij\7\5\2\2j\25\3\2\2\2kq"+
		"\7\7\2\2lq\5\30\r\2mq\7\6\2\2nq\7\4\2\2oq\7\3\2\2pk\3\2\2\2pl\3\2\2\2"+
		"pm\3\2\2\2pn\3\2\2\2po\3\2\2\2qt\3\2\2\2rp\3\2\2\2rs\3\2\2\2s\27\3\2\2"+
		"\2tr\3\2\2\2uv\t\2\2\2v\31\3\2\2\2\16 $\60=GITVbdpr";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}