package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class FocusGroupRepository @Inject() (dbConfigProvider: DatabaseConfigProvider) (implicit ec: ExecutionContext) {
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  private class FocusGroupTable(tag: Tag) extends Table[FocusGroup](tag, "focus_groups"){
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def country = column[String]("country")
    def technical_group = column[String]("technical_group")
    def age_group = column[String]("age_group")
    def challenges_faced = column[String]("challenges_faced")
    def connected_anonymously = column[String]("connected_anonymously")
    def anonymity_reasons = column[String]("anonymity_reasons")
    def anonymity_tools = column[String]("anonymity_tools")
    def used_VPN = column[String]("used_VPN")
    def vpn_use_reason = column[String]("vpn_use_reason")
    def gender = column[String]("gender")
    def used_smart_dns = column[String]("used_smart_dns")
    def smart_dns_reason = column[String]("smart_dns_reason")
    def preference = column[String]("preference")
    def shopping = column[Int]("shopping")
    def social_media = column[Int]("social_media")
    def entertainment = column[Int]("entertainment")
    def news = column[Int]("news")
    def educational_research = column[Int]("educational_research")
    def working = column[Int]("working")
    def comments = column[String]("comments")
    def vpn_challenges = column[String]("vpn_challenges")
    def * = (id, country, technical_group, age_group, challenges_faced,
    connected_anonymously, anonymity_reasons, anonymity_tools, used_VPN, vpn_use_reason, gender,
    used_smart_dns, smart_dns_reason, preference, shopping, social_media, entertainment, news, educational_research,
    working, comments, vpn_challenges) <> ((FocusGroup.apply _).tupled, FocusGroup.unapply)
  }
  private val focusGroup = TableQuery[FocusGroupTable]

  def list(): Future[Seq[FocusGroup]] = db.run {
    focusGroup.result
  }

  def count(): Future[Int] = db.run {
    focusGroup.length.result
  }
}
