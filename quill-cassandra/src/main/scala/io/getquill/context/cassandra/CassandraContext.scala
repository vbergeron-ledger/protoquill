package io.getquill.context.cassandra

import java.util.{ Date, UUID }
import com.datastax.driver.core.LocalDate
import io.getquill.NamingStrategy
import io.getquill.context.Context
import io.getquill.context.cassandra.encoding.{ CassandraMapper, Encodings, MapperSide }

import scala.reflect.ClassTag

trait CassandraContext[N <: NamingStrategy]
  extends Context[CqlIdiom, N]
  with Encodings
  //with UdtMetaDsl
  // with Ops
  {

  implicit def optionDecoder[T](implicit d: Decoder[T]): Decoder[Option[T]]
  implicit def optionEncoder[T](implicit d: Encoder[T]): Encoder[Option[T]]

  implicit val stringDecoder: Decoder[String]
  implicit val bigDecimalDecoder: Decoder[BigDecimal]
  implicit val booleanDecoder: Decoder[Boolean]
  implicit val byteDecoder: Decoder[Byte]
  implicit val shortDecoder: Decoder[Short]
  implicit val intDecoder: Decoder[Int]
  implicit val longDecoder: Decoder[Long]
  implicit val floatDecoder: Decoder[Float]
  implicit val doubleDecoder: Decoder[Double]
  implicit val byteArrayDecoder: Decoder[Array[Byte]]
  implicit val uuidDecoder: Decoder[UUID]
  implicit val timestampDecoder: Decoder[Date]
  implicit val cassandraLocalDateDecoder: Decoder[LocalDate]

  implicit val stringEncoder: Encoder[String]
  implicit val bigDecimalEncoder: Encoder[BigDecimal]
  implicit val booleanEncoder: Encoder[Boolean]
  implicit val byteEncoder: Encoder[Byte]
  implicit val shortEncoder: Encoder[Short]
  implicit val intEncoder: Encoder[Int]
  implicit val longEncoder: Encoder[Long]
  implicit val floatEncoder: Encoder[Float]
  implicit val doubleEncoder: Encoder[Double]
  implicit val byteArrayEncoder: Encoder[Array[Byte]]
  implicit val uuidEncoder: Encoder[UUID]
  implicit val timestampEncoder: Encoder[Date]
  implicit val cassandraLocalDateEncoder: Encoder[LocalDate]

  implicit def listDecoder[T, Cas](implicit mapper: CassandraMapper[Cas, T, MapperSide.Decode], ct: ClassTag[Cas]): Decoder[List[T]]
  implicit def setDecoder[T, Cas](implicit mapper: CassandraMapper[Cas, T, MapperSide.Decode], ct: ClassTag[Cas]): Decoder[Set[T]]
  implicit def mapDecoder[K, V, KCas, VCas](
    implicit
    keyMapper: CassandraMapper[KCas, K, MapperSide.Decode],
    valMapper: CassandraMapper[VCas, V, MapperSide.Decode],
    a: ClassTag[KCas],
    b: ClassTag[VCas]
  ): Decoder[Map[K, V]]

  implicit def listEncoder[T, Cas](implicit mapper: CassandraMapper[T, Cas, MapperSide.Encode]): Encoder[List[T]]
  implicit def setEncoder[T, Cas](implicit mapper: CassandraMapper[T, Cas, MapperSide.Encode]): Encoder[Set[T]]
  implicit def mapEncoder[K, V, KCas, VCas](
    implicit
    keyMapper: CassandraMapper[K, KCas, MapperSide.Encode],
    valMapper: CassandraMapper[V, VCas, MapperSide.Encode]
  ): Encoder[Map[K, V]]
}