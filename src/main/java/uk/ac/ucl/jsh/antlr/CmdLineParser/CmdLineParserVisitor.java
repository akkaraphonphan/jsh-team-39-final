// Generated from uk/ac/ucl/jsh/antlr/CmdLineParser/CmdLineParser.g4 by ANTLR 4.7.2
package uk.ac.ucl.jsh.antlr.CmdLineParser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CmdLineParserParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CmdLineParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CmdLineParserParser#compileUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompileUnit(CmdLineParserParser.CompileUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmdLineParserParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(CmdLineParserParser.CommandContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pipeBase}
	 * labeled alternative in {@link CmdLineParserParser#pipe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPipeBase(CmdLineParserParser.PipeBaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pipeRecursive}
	 * labeled alternative in {@link CmdLineParserParser#pipe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPipeRecursive(CmdLineParserParser.PipeRecursiveContext ctx);
	/**
	 * Visit a parse tree produced by the {@code seqRecursive}
	 * labeled alternative in {@link CmdLineParserParser#seq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeqRecursive(CmdLineParserParser.SeqRecursiveContext ctx);
	/**
	 * Visit a parse tree produced by the {@code seqBase}
	 * labeled alternative in {@link CmdLineParserParser#seq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeqBase(CmdLineParserParser.SeqBaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmdLineParserParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(CmdLineParserParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmdLineParserParser#single_quoted}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_quoted(CmdLineParserParser.Single_quotedContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmdLineParserParser#squote_content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSquote_content(CmdLineParserParser.Squote_contentContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmdLineParserParser#double_quoted}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble_quoted(CmdLineParserParser.Double_quotedContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmdLineParserParser#dquote_content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDquote_content(CmdLineParserParser.Dquote_contentContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmdLineParserParser#backquoted}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBackquoted(CmdLineParserParser.BackquotedContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmdLineParserParser#bquote_content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBquote_content(CmdLineParserParser.Bquote_contentContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmdLineParserParser#keyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword(CmdLineParserParser.KeywordContext ctx);
}