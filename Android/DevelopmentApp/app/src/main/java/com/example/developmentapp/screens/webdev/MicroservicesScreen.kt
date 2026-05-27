package com.example.developmentapp.screens.webdev

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.developmentapp.screens.BodyText
import com.example.developmentapp.screens.CodeBlock
import com.example.developmentapp.screens.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MicroservicesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Microservices",
                        color      = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize   = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector        = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint               = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding).padding(horizontal = 16.dp)) {
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Monolith vs Microservices") {
                    BodyText(
                        "Monolithic architecture — the entire application is one deployable unit. " +
                        "All features (user management, orders, payments, notifications) live in " +
                        "the same codebase, share the same process, and are deployed together.\n\n" +
                        "Advantages of monolith: simple to develop initially, easy to debug " +
                        "(single process), no network overhead between components.\n\n" +
                        "Problems at scale: a bug in one feature can crash everything; " +
                        "you must redeploy the whole app for any change; teams block each other; " +
                        "you can't scale a specific bottleneck independently.\n\n" +
                        "Microservices — split the application into small, independently " +
                        "deployable services, each responsible for one business capability. " +
                        "Each service has its own process, its own database, its own deployment.\n\n" +
                        "Tradeoff: microservices solve scaling and team autonomy problems, " +
                        "but introduce distributed system complexity — network calls, service " +
                        "discovery, distributed tracing, eventual consistency."
                    )
                    CodeBlock(
                        "// Monolith — everything in one process\n" +
                        "MyApp (single deploy)\n" +
                        "  ├── UserModule\n" +
                        "  ├── OrderModule\n" +
                        "  ├── PaymentModule\n" +
                        "  └── NotificationModule\n\n" +
                        "// Microservices — separate deployable services\n" +
                        "UserService     → port 3001 → users DB\n" +
                        "OrderService    → port 3002 → orders DB\n" +
                        "PaymentService  → port 3003 → payments DB\n" +
                        "NotifyService   → port 3004 → email/SMS queue"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Service Boundaries") {
                    BodyText(
                        "Each microservice owns its data — no sharing of databases between services. " +
                        "If OrderService needs user information, it calls UserService via API; " +
                        "it does not directly query the users database.\n\n" +
                        "This enforces loose coupling: OrderService can be rewritten from Python " +
                        "to Go without any other service knowing, as long as the API contract " +
                        "remains the same.\n\n" +
                        "Bounded context (from Domain-Driven Design): each service maps to one " +
                        "logical domain with clear responsibilities. Good boundaries follow business " +
                        "capabilities, not technical layers."
                    )
                    CodeBlock(
                        "// BAD: services sharing a database (tight coupling)\n" +
                        "OrderService → SELECT * FROM users.users WHERE id = 42\n\n" +
                        "// GOOD: API call (loose coupling)\n" +
                        "OrderService → GET http://user-service/users/42\n" +
                        "             ← { id: 42, name: 'Alice', email: '...' }\n\n" +
                        "// Each service owns its data:\n" +
                        "UserService    owns: users_db.users, users_db.profiles\n" +
                        "OrderService   owns: orders_db.orders, orders_db.order_items\n" +
                        "PaymentService owns: payments_db.transactions"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Communication Patterns") {
                    BodyText(
                        "Synchronous (both sides wait):\n\n" +
                        "HTTP REST / gRPC — direct call from ServiceA to ServiceB. " +
                        "Simple to understand; if ServiceB is down, ServiceA fails. " +
                        "Creates tight temporal coupling.\n\n" +
                        "Asynchronous (fire and forget):\n\n" +
                        "Message Queue (RabbitMQ, AWS SQS) — ServiceA publishes a message to " +
                        "a queue; ServiceB consumes it when ready. If ServiceB is down, messages " +
                        "wait in the queue; no data lost. Services are temporally decoupled.\n\n" +
                        "Event Streaming (Apache Kafka) — durable, ordered event log. " +
                        "Multiple consumers can read the same events independently. " +
                        "Great for audit logs, event sourcing, and building read models.\n\n" +
                        "Event-driven architecture — services react to events rather than " +
                        "directly calling each other. When OrderService creates an order, it " +
                        "emits 'order.created'; NotifyService listens and sends a confirmation email."
                    )
                    CodeBlock(
                        "// Synchronous: HTTP REST\n" +
                        "OrderService → POST http://payment-service/charges\n" +
                        "             → waits for response...\n" +
                        "             ← { status: 'approved', chargeId: 'ch_abc' }\n\n" +
                        "// Asynchronous: message queue event\n" +
                        "OrderService publishes: {\n" +
                        "    event: 'order.created',\n" +
                        "    orderId: 'ord_123',\n" +
                        "    userId: 42,\n" +
                        "    total: 5000\n" +
                        "}\n\n" +
                        "PaymentService subscribes → charges the card\n" +
                        "NotifyService  subscribes → sends confirmation email\n" +
                        "// Both receive the same event independently"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Horizontal Scaling") {
                    BodyText(
                        "One of the main reasons for microservices: you can scale individual " +
                        "services independently based on their specific load.\n\n" +
                        "Vertical scaling (scale up) — add more CPU/RAM to the existing server. " +
                        "Limited and expensive.\n\n" +
                        "Horizontal scaling (scale out) — add more instances of the same service. " +
                        "Effectively unlimited; each instance handles a share of the traffic.\n\n" +
                        "Example: it's Black Friday. OrderService is under 10x normal load, but " +
                        "UserService load is normal. Scale OrderService from 2 to 20 instances; " +
                        "leave UserService at 2. In a monolith, you'd have to scale everything.\n\n" +
                        "Containers (Docker) make horizontal scaling fast — a new container " +
                        "starts in seconds vs minutes for a full VM."
                    )
                    CodeBlock(
                        "// Normal operation\n" +
                        "OrderService:   2 instances  (handling 100 req/s)\n" +
                        "UserService:    2 instances  (handling  50 req/s)\n" +
                        "PaymentService: 2 instances  (handling  40 req/s)\n\n" +
                        "// Black Friday: spike on orders\n" +
                        "OrderService:   20 instances  ← scaled out\n" +
                        "UserService:     2 instances  ← unchanged\n" +
                        "PaymentService:  8 instances  ← slightly scaled\n\n" +
                        "// Docker: run a new instance in seconds\n" +
                        "docker run -d order-service:v2.1"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Load Balancer") {
                    BodyText(
                        "A load balancer sits in front of multiple service instances and " +
                        "distributes incoming requests so no single instance is overwhelmed.\n\n" +
                        "Distribution algorithms:\n" +
                        "• Round-robin — requests go to each instance in turn (1,2,3,1,2,3...)\n" +
                        "• Least connections — next request goes to the instance with fewest " +
                        "  active connections. Better for variable-length requests.\n" +
                        "• IP hash — same client IP always goes to same instance. " +
                        "  Useful for sticky sessions.\n" +
                        "• Weighted — some instances get more traffic (new version rollout)\n\n" +
                        "Health checks — load balancer periodically pings each instance. " +
                        "If an instance fails to respond, it is removed from rotation automatically.\n\n" +
                        "Common load balancers:\n" +
                        "• Nginx — also a reverse proxy and web server\n" +
                        "• HAProxy — specialized, high-performance\n" +
                        "• AWS ALB (Application Load Balancer) — managed, Layer 7, integrates with ECS/EKS\n" +
                        "• GCP Cloud Load Balancing — global, anycast\n" +
                        "• Kubernetes Service + Ingress — built-in k8s load balancing"
                    )
                    CodeBlock(
                        "// Nginx as load balancer (upstream block)\n" +
                        "upstream order_service {\n" +
                        "    least_conn;  # least-connections algorithm\n" +
                        "    server 10.0.0.1:3002;\n" +
                        "    server 10.0.0.2:3002;\n" +
                        "    server 10.0.0.3:3002;\n" +
                        "}\n\n" +
                        "server {\n" +
                        "    listen 80;\n" +
                        "    location /api/orders {\n" +
                        "        proxy_pass http://order_service;\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Auto-Scaling") {
                    BodyText(
                        "Auto-scaling automatically adds or removes instances based on observed " +
                        "metrics — no human needs to intervene during a traffic spike.\n\n" +
                        "Kubernetes HPA (Horizontal Pod Autoscaler) — the most common approach " +
                        "in containerized environments:\n" +
                        "• Watches CPU utilization, memory, or custom metrics (requests/sec via Prometheus)\n" +
                        "• If CPU > 70% for 2 minutes → increase replicas\n" +
                        "• If CPU < 20% for 5 minutes → decrease replicas (after cooldown)\n" +
                        "• Bounded by minReplicas and maxReplicas\n\n" +
                        "AWS Auto Scaling Groups — for EC2 instances; same concept.\n" +
                        "AWS ECS Service Auto Scaling — for ECS containers.\n" +
                        "GCP Managed Instance Groups — for GCE VMs.\n\n" +
                        "Scale-down cooldown period — prevents flapping (rapid up/down cycles) " +
                        "when load hovers around the threshold."
                    )
                    CodeBlock(
                        "# Kubernetes HPA YAML\n" +
                        "apiVersion: autoscaling/v2\n" +
                        "kind: HorizontalPodAutoscaler\n" +
                        "metadata:\n" +
                        "  name: order-service-hpa\n" +
                        "spec:\n" +
                        "  scaleTargetRef:\n" +
                        "    apiVersion: apps/v1\n" +
                        "    kind: Deployment\n" +
                        "    name: order-service\n" +
                        "  minReplicas: 2\n" +
                        "  maxReplicas: 20\n" +
                        "  metrics:\n" +
                        "    - type: Resource\n" +
                        "      resource:\n" +
                        "        name: cpu\n" +
                        "        target:\n" +
                        "          type: Utilization\n" +
                        "          averageUtilization: 70  # scale up if CPU > 70%"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Orchestration — Kubernetes") {
                    BodyText(
                        "Orchestration manages the scheduling, scaling, networking, and health " +
                        "of containers across a cluster of machines.\n\n" +
                        "Kubernetes (k8s) — the dominant container orchestration platform, " +
                        "open-sourced by Google in 2014.\n\n" +
                        "Core concepts:\n" +
                        "• Pod — smallest deployable unit; one or more containers sharing " +
                        "  network/storage. Ephemeral — can be killed and replaced.\n" +
                        "• Deployment — describes desired state: run N replicas of this pod. " +
                        "  Kubernetes ensures that many pods are always running.\n" +
                        "• Service — a stable network endpoint (IP + port) in front of a " +
                        "  group of pods. Pods can change; the Service IP stays constant.\n" +
                        "• Ingress — HTTP routing rules. Maps incoming URLs to backend Services: " +
                        "  /api/orders → order-service, /api/users → user-service.\n" +
                        "• ConfigMap / Secret — inject configuration and credentials into pods\n" +
                        "• Namespace — logical cluster for isolating environments (dev/staging/prod)\n\n" +
                        "Alternatives: Docker Swarm (simpler), HashiCorp Nomad (more general)."
                    )
                    CodeBlock(
                        "# Kubernetes Deployment YAML\n" +
                        "apiVersion: apps/v1\n" +
                        "kind: Deployment\n" +
                        "metadata:\n" +
                        "  name: order-service\n" +
                        "spec:\n" +
                        "  replicas: 3\n" +
                        "  selector:\n" +
                        "    matchLabels:\n" +
                        "      app: order-service\n" +
                        "  template:\n" +
                        "    spec:\n" +
                        "      containers:\n" +
                        "        - name: order-service\n" +
                        "          image: my-registry/order-service:v2.1\n" +
                        "          ports:\n" +
                        "            - containerPort: 3002\n" +
                        "          resources:\n" +
                        "            requests: { cpu: '100m', memory: '128Mi' }\n" +
                        "            limits:   { cpu: '500m', memory: '512Mi' }"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Challenges") {
                    BodyText(
                        "Microservices are not a silver bullet — they introduce real complexity:\n\n" +
                        "Distributed tracing — a single user request may span 5 services. " +
                        "When something fails, which service caused it? Tools: Jaeger, Zipkin, " +
                        "AWS X-Ray. Each request gets a trace ID that propagates through services.\n\n" +
                        "Service discovery — how does ServiceA know the IP of ServiceB? " +
                        "In k8s: built-in DNS. Tools: Consul, Eureka.\n\n" +
                        "Network latency — what was a local function call is now a network " +
                        "round-trip with serialization/deserialization overhead.\n\n" +
                        "Eventual consistency — since each service has its own database, data " +
                        "across services may be temporarily inconsistent. " +
                        "Two-phase commit is too slow; saga pattern handles distributed transactions.\n\n" +
                        "Debugging — reproducing production issues across many services locally " +
                        "is hard. Comprehensive logging + correlation IDs are essential.\n\n" +
                        "Operational overhead — you need CI/CD pipelines, container registries, " +
                        "Kubernetes expertise, monitoring, alerting. " +
                        "Start with a well-structured monolith; extract services only when you feel pain."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
