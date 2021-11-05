package io.getquill

import com.datastax.driver.core._
import io.getquill.context.{ ExecutionInfo, StandardContext }
import io.getquill.context.cassandra.{ CassandraRowContext, CqlIdiom }

import scala.jdk.CollectionConverters._
import scala.util.Try
import io.getquill.context.cassandra.CassandraStandardContext
import io.getquill.context.Context
import io.getquill.context.cassandra.CassandraPrepareContext
import io.getquill.context.AsyncFutureCache

class CassandraFs2Context[F[_], N <: NamingStrategy](val naming: N)
  extends CassandraStandardContext[N]
  with Fs2Context[F, CqlIdiom, N]
  with Context[CqlIdiom, N] {

  private val logger = ContextLogger(classOf[CassandraFs2Context[_]])

  override type RunBatchActionResult = Unit

  override type PrepareRow = BoundStatement
  override type ResultRow = Row
  override type Session = CassandraZioSession

  override type DatasourceContext = Unit
  override protected def context: DatasourceContext = ()

  def streamQuery[T](fetchSize: Option[Int], cql: String, prepare: Prepare = identityPrepare, extractor: Extractor[T] = identityExtractor)(info: ExecutionInfo, dc: DatasourceContext) = {
  }

  def executeQuery[T](cql: String, prepare: Prepare = identityPrepare, extractor: Extractor[T] = identityExtractor)(info: ExecutionInfo, dc: DatasourceContext): CIO[List[T]] = simpleBlocking {
  }

  def executeQuerySingle[T](cql: String, prepare: Prepare = identityPrepare, extractor: Extractor[T] = identityExtractor)(info: ExecutionInfo, dc: DatasourceContext): CIO[T] = simpleBlocking {
  }

  def executeAction[T](cql: String, prepare: Prepare = identityPrepare)(info: ExecutionInfo, dc: DatasourceContext): CIO[Unit] = simpleBlocking {
  }

  def executeBatchAction(groups: List[BatchGroup])(info: ExecutionInfo, dc: DatasourceContext): CIO[Unit] = simpleBlocking {
  }

  def probingSession: Option[CassandraZioSession] = None

  def probe(statement: String): scala.util.Try[_] = {
    probingSession match {
      case Some(csession) =>
        Try(csession.prepare(statement))
      case None =>
        Try(())
    }
  }

  override def close(): Unit = ???
}
