package io.getquill.context.fs2

import fs2.Stream

trait FS2Context[F[_], Idiom <: io.getquill.idiom.Idiom, Naming <: NamingStrategy] extends Context[Idiom, Naming]
  with StreamingContext[Idiom, Naming] {

  override type StreamResult[T] = Stream[F, T]
  override type Result[T] = F[T]
  override type RunQueryResult[T] = List[T]
  override type RunQuerySingleResult[T] = T

}
